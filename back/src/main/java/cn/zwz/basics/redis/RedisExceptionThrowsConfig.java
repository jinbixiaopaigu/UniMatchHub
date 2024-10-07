package cn.zwz.basics.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.Duration;

/**
 * Redis 缓存异常处理配置
 */
@Configuration
@Slf4j
public class RedisExceptionThrowsConfig extends CachingConfigurerSupport {

    @Value("${cache.expire.unit:day}")
    @Schema(description = "时长类型", example = "day")
    private String unit;

    @Value("${cache.expire.time:30}")
    @Schema(description = "时长值, -1为不限制", example = "30")
    private Integer time;

    @Override
    @Bean
    public CacheErrorHandler errorHandler() {
        return new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
                log.warn("Redis序列化查询异常: {}", key, e);
            }

            @Override
            public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
                log.warn("Redis序列化插入异常: {}", key, e);
            }

            @Override
            public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
                log.warn("Redis序列化Evict异常: {}", key, e);
            }

            @Override
            public void handleCacheClearError(RuntimeException e, Cache cache) {
                log.warn("Redis序列化删除异常", e);
            }
        };
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        // 使用 StringRedisSerializer 序列化 Redis 的 key
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();

        // 使用 Jackson2ObjectMapperBuilder 创建 ObjectMapper
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
                .visibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                .build();

        // 配置 PolymorphicTypeValidator 以便安全地启用多态类型处理
        BasicPolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
                .allowIfBaseType(Object.class)
                .build();

        // 使用 PolymorphicTypeValidator 进行类型安全的多态配置
        objectMapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);

        // 直接使用构造函数创建 Jackson2JsonRedisSerializer，传入自定义 ObjectMapper
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(objectMapper, Object.class);

        // 配置 RedisCacheConfiguration，使用自定义的序列化器
        RedisCacheConfiguration rc = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer))
                .disableCachingNullValues();

        // 设置缓存过期时间
        Duration expireTime = Duration.ofDays(time);
        if ("hour".equalsIgnoreCase(unit)) {
            expireTime = Duration.ofHours(time);
        } else if ("minute".equalsIgnoreCase(unit)) {
            expireTime = Duration.ofMinutes(time);
        }

        return RedisCacheManager.builder(factory).cacheDefaults(rc.entryTtl(expireTime)).build();
    }
}
