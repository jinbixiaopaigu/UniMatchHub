package cn.zwz.basics.redis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;


//@Operation(description = "Redis工具类")
@Component
public class RedisTemplateHelper {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Operation(description = "scan实现")
    private void scan(String wayForScan, Consumer<byte[]> consumableList) {
        redisTemplate.execute((RedisConnection connection) -> {
            try (Cursor<byte[]> cursor = connection.scan(ScanOptions.scanOptions().count(Long.MAX_VALUE).match(wayForScan).build())) {
                cursor.forEachRemaining(consumableList);
                return null;
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        });
    }

    @Operation(description = "scan获取符合条件的key")
    public Set<String> scan(String pattern) {

        Set<String> keys = new HashSet<>();
        this.scan(pattern, item -> {
            String key = new String(item, StandardCharsets.UTF_8);
            keys.add(key);
        });
        return keys;
    }

    @Operation(description = "通过通配符表达式删除所有")
    public void deleteByPattern(String pattern) {
        Set<String> keys = this.scan(pattern);
        redisTemplate.delete(keys);
    }

    @Operation(description = "删除key")
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Operation(description = "批量删除key")
    public void delete(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    @Operation(description = "序列化key")
    public byte[] dump(String key) {
        return redisTemplate.dump(key);
    }

    @Operation(description = "是否存在key")
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    @Operation(description = "设置过期时间")
    public Boolean expire(String key, long timeout, TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    @Operation(description = "设置过期时间")
    public Boolean expireAt(String key, Date date) {
        return redisTemplate.expireAt(key, date);
    }

    @Operation(description = "查找匹配的key")
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    @Operation(description = "将当前数据库的 key 移动到给定的数据库 db 当中")
    public Boolean move(String key, int dbIndex) {
        return redisTemplate.move(key, dbIndex);
    }

    @Operation(description = "移除 key 的过期时间，key 将持久保持")
    public Boolean persist(String key) {
        return redisTemplate.persist(key);
    }

    @Operation(description = "返回 key 的剩余的过期时间")
    public Long getExpire(String key, TimeUnit unit) {
        return redisTemplate.getExpire(key, unit);
    }

    @Operation(description = "返回 key 的剩余的过期时间")
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    @Operation(description = "从当前数据库中随机返回一个 key")
    public String randomKey() {
        return redisTemplate.randomKey();
    }

    @Operation(description = "修改 key 的名称")
    public void rename(String oldKey, String newKey) {
        redisTemplate.rename(oldKey, newKey);
    }

    @Operation(description = "仅当 newkey 不存在时，将 oldKey 改名为 newkey")
    public Boolean renameIfAbsent(String oldKey, String newKey) {
        return redisTemplate.renameIfAbsent(oldKey, newKey);
    }

    @Operation(description = "返回 key 所储存的值的类型")
    public DataType type(String key) {
        return redisTemplate.type(key);
    }

    /** -------------------string相关操作--------------------- */

    @Operation(description = "设置指定 key 的值")
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Operation(description = "将值 value 关联到 key ，并将 key 的过期时间设为 timeout")
    public void set(String key, String value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    @Operation(description = "获取指定 key 的值")
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Operation(description = "返回 key 中字符串值的子字符")
    public String getRange(String key, long start, long end) {
        return redisTemplate.opsForValue().get(key, start, end);
    }

    @Operation(description = "将给定 key 的值设为 value ，并返回 key 的旧值(old value)")
    public String getAndSet(String key, String value) {
        return redisTemplate.opsForValue().getAndSet(key, value);
    }

    @Operation(description = "对 key 所储存的字符串值，获取指定偏移量上的位(bit)")
    public Boolean getBit(String key, long offset) {
        return redisTemplate.opsForValue().getBit(key, offset);
    }

    @Operation(description = "批量获取")
    public List<String> multiGet(Collection<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    @Operation(summary = "只有在 key 不存在时设置 key 的值", description = "之前已经存在返回 false, 不存在返回 true")
    public boolean setBit(String key, long offset, boolean value) {
        return redisTemplate.opsForValue().setBit(key, offset, value);
    }

    @Operation(summary = "只有在 key 不存在时设置 key 的值", description = "之前已经存在返回 false, 不存在返回 true")
    public boolean setIfAbsent(String key, String value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    @Operation(summary = "用 value 参数覆写给定 key 所储存的字符串值，从偏移量 offset 开始", description = "offset: 从指定位置开始覆写")
    public void setRange(String key, String value, long offset) {
        redisTemplate.opsForValue().set(key, value, offset);
    }

    @Operation(description = "获取字符串的长度")
    public Long size(String key) {
        return redisTemplate.opsForValue().size(key);
    }

    @Operation(description = "批量添加")
    public void multiSet(Map<String, String> maps) {
        redisTemplate.opsForValue().multiSet(maps);
    }

    @Operation(description = "同时设置一个或多个 key-value 对，当且仅当所有给定 key 都不存在")
    public boolean multiSetIfAbsent(Map<String, String> maps) {
        return redisTemplate.opsForValue().multiSetIfAbsent(maps);
    }

    @Operation(description = "增加(自增长), 负数则为自减")
    public Long incrBy(String key, long increment) {
        return redisTemplate.opsForValue().increment(key, increment);
    }

    @Operation(description = "增加(自增长)")
    public Double incrByFloat(String key, double increment) {
        return redisTemplate.opsForValue().increment(key, increment);
    }

    @Operation(description = "追加到末尾")
    public Integer append(String key, String value) {
        return redisTemplate.opsForValue().append(key, value);
    }

    // hash表
    @Operation(description = "获取存储在哈希表中指定字段的值")
    public Object hGet(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    @Operation(description = "获取所有给定字段的值")
    public Map<Object, Object> hGetAll(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    @Operation(description = "获取所有给定字段的值")
    public List<Object> hMultiGet(String key, Collection<Object> fields) {
        return redisTemplate.opsForHash().multiGet(key, fields);
    }

    @Operation(description = "添加单个")
    public void hPut(String key, String hashKey, String value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    @Operation(description = "添加集合")
    public void hPutAll(String key, Map<String, String> maps) {
        redisTemplate.opsForHash().putAll(key, maps);
    }

    @Operation(description = "仅当hashKey不存在时才设置")
    public Boolean hPutIfAbsent(String key, String hashKey, String value) {
        return redisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
    }

    @Operation(description = "删除一个或多个哈希表字段")
    public Long hDelete(String key, Object... fields) {
        return redisTemplate.opsForHash().delete(key, fields);
    }

    @Operation(description = "查看哈希表 key 中，指定的字段是否存在")
    public boolean hExists(String key, String field) {
        return redisTemplate.opsForHash().hasKey(key, field);
    }

    @Operation(description = "为哈希表 key 中的指定字段的整数值加上增量 increment")
    public Long hIncrBy(String key, Object field, long increment) {
        return redisTemplate.opsForHash().increment(key, field, increment);
    }

    @Operation(description = "为哈希表 key 中的指定字段的整数值加上增量 increment")
    public Double hIncrByFloat(String key, Object field, double delta) {
        return redisTemplate.opsForHash().increment(key, field, delta);
    }

    @Operation(description = "获取所有哈希表中的字段")
    public Set<Object> hKeys(String key) {
        return redisTemplate.opsForHash().keys(key);
    }

    @Operation(description = "获取哈希表中字段的数量")
    public Long hSize(String key) {
        return redisTemplate.opsForHash().size(key);
    }

    @Operation(description = "获取哈希表中所有值")
    public List<Object> hValues(String key) {
        return redisTemplate.opsForHash().values(key);
    }

    @Operation(description = "迭代哈希表中的键值对")
    public Cursor<Map.Entry<Object, Object>> hScan(String key, ScanOptions options) {
        return redisTemplate.opsForHash().scan(key, options);
    }

    // list
    @Operation(description = "通过索引获取列表中的元素")
    public String lIndex(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    @Operation(description = "获取列表指定范围内的元素")
    public List<String> lRange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    @Operation(description = "存储在list头部")
    public Long lLeftPush(String key, String value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    public Long lLeftPushAll(String key, String... value) {
        return redisTemplate.opsForList().leftPushAll(key, value);
    }

    public Long lLeftPushAll(String key, Collection<String> value) {
        return redisTemplate.opsForList().leftPushAll(key, value);
    }

    @Operation(description = "当list存在的时候才加入")
    public Long lLeftPushIfPresent(String key, String value) {
        return redisTemplate.opsForList().leftPushIfPresent(key, value);
    }

    @Operation(description = "如果pivot存在,再pivot前面添加")
    public Long lLeftPush(String key, String pivot, String value) {
        return redisTemplate.opsForList().leftPush(key, pivot, value);
    }

    public Long lRightPush(String key, String value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    public Long lRightPushAll(String key, String... value) {
        return redisTemplate.opsForList().rightPushAll(key, value);
    }

    public Long lRightPushAll(String key, Collection<String> value) {
        return redisTemplate.opsForList().rightPushAll(key, value);
    }

    @Operation(description = "为已存在的列表添加值")
    public Long lRightPushIfPresent(String key, String value) {
        return redisTemplate.opsForList().rightPushIfPresent(key, value);
    }

    @Operation(description = "在pivot元素的右边添加值")
    public Long lRightPush(String key, String pivot, String value) {
        return redisTemplate.opsForList().rightPush(key, pivot, value);
    }

    @Operation(description = "通过索引设置列表元素的值")
    public void lSet(String key, long index, String value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    @Operation(description = "移出并获取列表的第一个元素")
    public String lLeftPop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    @Operation(description = "移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止")
    public String lBLeftPop(String key, long timeout, TimeUnit unit) {
        return redisTemplate.opsForList().leftPop(key, timeout, unit);
    }

    @Operation(description = "移除并获取列表最后一个元素")
    public String lRightPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    @Operation(description = "移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止")
    public String lBRightPop(String key, long timeout, TimeUnit unit) {
        return redisTemplate.opsForList().rightPop(key, timeout, unit);
    }

    @Operation(description = "移除列表的最后一个元素，并将该元素添加到另一个列表并返回")
    public String lRightPopAndLeftPush(String sourceKey, String destinationKey) {
        return redisTemplate.opsForList().rightPopAndLeftPush(sourceKey,
                destinationKey);
    }

    @Operation(description = "从列表中弹出一个值，将弹出的元素插入到另外一个列表中并返回它； 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止")
    public String lBRightPopAndLeftPush(String sourceKey, String destinationKey,long timeout, TimeUnit unit) {
        return redisTemplate.opsForList().rightPopAndLeftPush(sourceKey,destinationKey, timeout, unit);
    }

    @Operation(summary = "删除集合中值等于 value 的元素", description = "index=0, 删除所有值等于 value 的元素; index>0, 从头部开始删除第一个值等于 value 的元素; index<0, 从尾部开始删除第一个值等于 value 的元素")
    public Long lRemove(String key, long index, String value) {
        return redisTemplate.opsForList().remove(key, index, value);
    }

    @Operation(description = "裁剪list")
    public void lTrim(String key, long start, long end) {
        redisTemplate.opsForList().trim(key, start, end);
    }

    @Operation(description = "获取列表长度")
    public Long lLen(String key) {
        return redisTemplate.opsForList().size(key);
    }

    // set
    @Operation(description = "set添加元素")
    public Long sAdd(String key, String... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    @Operation(description = "set移除元素")
    public Long sRemove(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    @Operation(description = "移除并返回集合的一个随机元素")
    public String sPop(String key) {
        return redisTemplate.opsForSet().pop(key);
    }

    @Operation(description = "将元素value从一个集合移到另一个集合")
    public Boolean sMove(String key, String value, String destKey) {
        return redisTemplate.opsForSet().move(key, value, destKey);
    }

    @Operation(description = "获取集合的大小")
    public Long sSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    @Operation(description = "判断集合是否包含value")
    public Boolean sIsMember(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    @Operation(description = "获取两个集合的交集")
    public Set<String> sIntersect(String key, String otherKey) {
        return redisTemplate.opsForSet().intersect(key, otherKey);
    }

    @Operation(description = "获取key集合与多个集合的交集")
    public Set<String> sIntersect(String key, Collection<String> otherKeys) {
        return redisTemplate.opsForSet().intersect(key, otherKeys);
    }

    @Operation(description = "key集合与otherKey集合的交集存储到destKey集合中")
    public Long sIntersectAndStore(String key, String otherKey, String destKey) {
        return redisTemplate.opsForSet().intersectAndStore(key, otherKey, destKey);
    }

    @Operation(description = "key集合与多个集合的交集存储到destKey集合中")
    public Long sIntersectAndStore(String key, Collection<String> otherKeys,String destKey) {
        return redisTemplate.opsForSet().intersectAndStore(key, otherKeys, destKey);
    }

    @Operation(description = "获取两个集合的并集")
    public Set<String> sUnion(String key, String otherKeys) {
        return redisTemplate.opsForSet().union(key, otherKeys);
    }

    @Operation(description = "获取key集合与多个集合的并集")
    public Set<String> sUnion(String key, Collection<String> otherKeys) {
        return redisTemplate.opsForSet().union(key, otherKeys);
    }

    @Operation(description = "key集合与otherKey集合的并集存储到destKey中")
    public Long sUnionAndStore(String key, String otherKey, String destKey) {
        return redisTemplate.opsForSet().unionAndStore(key, otherKey, destKey);
    }

    @Operation(description = "key集合与多个集合的并集存储到destKey中")
    public Long sUnionAndStore(String key, Collection<String> otherKeys,String destKey) {
        return redisTemplate.opsForSet().unionAndStore(key, otherKeys, destKey);
    }

    @Operation(description = "获取两个集合的差集")
    public Set<String> sDifference(String key, String otherKey) {
        return redisTemplate.opsForSet().difference(key, otherKey);
    }

    @Operation(description = "获取key集合与多个集合的差集")
    public Set<String> sDifference(String key, Collection<String> otherKeys) {
        return redisTemplate.opsForSet().difference(key, otherKeys);
    }

    @Operation(description = "key集合与otherKey集合的差集存储到destKey中")
    public Long sDifference(String key, String otherKey, String destKey) {
        return redisTemplate.opsForSet().differenceAndStore(key, otherKey, destKey);
    }

    @Operation(description = "key集合与多个集合的差集存储到destKey中")
    public Long sDifference(String key, Collection<String> otherKeys,String destKey) {
        return redisTemplate.opsForSet().differenceAndStore(key, otherKeys,destKey);
    }

    @Operation(description = "返回集合指定元素")
    public Set<String> setMembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    @Operation(description = "随机获取集合中的一个元素")
    public String sRandomMember(String key) {
        return redisTemplate.opsForSet().randomMember(key);
    }

    @Operation(description = "随机获取集合中count个元素")
    public List<String> sRandomMembers(String key, long count) {
        return redisTemplate.opsForSet().randomMembers(key, count);
    }

    @Operation(description = "随机获取集合中count个元素并且去除重复的")
    public Set<String> sDistinctRandomMembers(String key, long count) {
        return redisTemplate.opsForSet().distinctRandomMembers(key, count);
    }

    @Operation(description = "scan扫描返回指定key")
    public Cursor<String> sScan(String key, ScanOptions options) {
        return redisTemplate.opsForSet().scan(key, options);
    }

    // zSet
    @Operation(description = "添加元素,有序集合是按照元素的score值由小到大排列")
    public Boolean zAdd(String key, String value, double score) {
        return redisTemplate.opsForZSet().add(key, value, score);
    }

    @Operation(description = "添加集合")
    public Long zAdd(String key, Set<ZSetOperations.TypedTuple<String>> values) {
        return redisTemplate.opsForZSet().add(key, values);
    }

    @Operation(description = "移除")
    public Long zRemove(String key, Object... values) {
        return redisTemplate.opsForZSet().remove(key, values);
    }

    @Operation(description = "增加元素的score值，并返回增加后的值")
    public Double zIncrementScore(String key, String value, double delta) {
        return redisTemplate.opsForZSet().incrementScore(key, value, delta);
    }

    @Operation(description = "返回元素在集合的排名,有序集合是按照元素的score值由小到大排列")
    public Long zRank(String key, Object value) {
        return redisTemplate.opsForZSet().rank(key, value);
    }

    @Operation(description = "返回元素在集合的排名,按元素的score值由大到小排列")
    public Long zReverseRank(String key, Object value) {
        return redisTemplate.opsForZSet().reverseRank(key, value);
    }

    @Operation(description = "获取集合的元素, 从小到大排序")
    public Set<String> zRange(String key, long start, long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    @Operation(description = "获取集合元素, 并且把score值也获取")
    public Set<ZSetOperations.TypedTuple<String>> zRangeWithScores(String key, long start,long end) {
        return redisTemplate.opsForZSet().rangeWithScores(key, start, end);
    }

    @Operation(description = "根据Score值查询集合元素")
    public Set<String> zRangeByScore(String key, double min, double max) {
        return redisTemplate.opsForZSet().rangeByScore(key, min, max);
    }

    @Operation(description = "根据Score值查询集合元素, 从小到大排序")
    public Set<ZSetOperations.TypedTuple<String>> zRangeByScoreWithScores(String key,double min, double max) {
        return redisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max);
    }

    @Operation(description = "根据Score值和指定位置查询集合元素, 从小到大排序")
    public Set<ZSetOperations.TypedTuple<String>> zRangeByScoreWithScores(String key,double min, double max, long start, long end) {
        return redisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max, start, end);
    }

    @Operation(description = "获取集合的元素, 从大到小排序")
    public Set<String> zReverseRange(String key, long start, long end) {
        return redisTemplate.opsForZSet().reverseRange(key, start, end);
    }

    @Operation(description = "获取集合的元素, 从大到小排序, 并返回score值")
    public Set<ZSetOperations.TypedTuple<String>> zReverseRangeWithScores(String key,long start, long end) {
        return redisTemplate.opsForZSet().reverseRangeWithScores(key, start, end);
    }

    @Operation(description = "根据Score值查询集合元素, 从大到小排序")
    public Set<String> zReverseRangeByScore(String key, double min, double max) {
        return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max);
    }

    @Operation(description = "根据Score值查询集合元素, 从大到小排序")
    public Set<ZSetOperations.TypedTuple<String>> zReverseRangeByScoreWithScores(String key, double min, double max) {
        return redisTemplate.opsForZSet().reverseRangeByScoreWithScores(key, min, max);
    }

    @Operation(description = "根据Score值和指定位置查询集合元素, 从大到小排序")
    public Set<String> zReverseRangeByScore(String key, double min, double max, long start, long end) {
        return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max, start, end);
    }

    @Operation(description = "根据score值获取集合元素数量")
    public Long zCount(String key, double min, double max) {
        return redisTemplate.opsForZSet().count(key, min, max);
    }

    @Operation(description = "获取集合大小")
    public Long zSize(String key) {
        return redisTemplate.opsForZSet().size(key);
    }

    @Operation(description = "获取集合大小")
    public Long zZCard(String key) {
        return redisTemplate.opsForZSet().zCard(key);
    }

    @Operation(description = "获取集合中value元素的score值")
    public Double zScore(String key, Object value) {
        return redisTemplate.opsForZSet().score(key, value);
    }

    @Operation(description = "移除指定索引位置的成员")
    public Long zRemoveRange(String key, long start, long end) {
        return redisTemplate.opsForZSet().removeRange(key, start, end);
    }

    @Operation(description = "根据指定的score值的范围来移除成员")
    public Long zRemoveRangeByScore(String key, double min, double max) {
        return redisTemplate.opsForZSet().removeRangeByScore(key, min, max);
    }

    @Operation(description = "获取key和otherKey的并集并存储在destKey中")
    public Long zUnionAndStore(String key, String otherKey, String destKey) {
        return redisTemplate.opsForZSet().unionAndStore(key, otherKey, destKey);
    }

    @Operation(description = "获取key和otherKey的并集并存储在destKey中")
    public Long zUnionAndStore(String key, Collection<String> otherKeys, String destKey) {
        return redisTemplate.opsForZSet().unionAndStore(key, otherKeys, destKey);
    }

    @Operation(description = "交集")
    public Long zIntersectAndStore(String key, String otherKey, String destKey) {
        return redisTemplate.opsForZSet().intersectAndStore(key, otherKey, destKey);
    }

    @Operation(description = "交集")
    public Long zIntersectAndStore(String key, Collection<String> otherKeys, String destKey) {
        return redisTemplate.opsForZSet().intersectAndStore(key, otherKeys, destKey);
    }

    @Operation(description = "scan扫描指定key")
    public Cursor<ZSetOperations.TypedTuple<String>> zScan(String key, ScanOptions options) {
        return redisTemplate.opsForZSet().scan(key, options);
    }

    @Operation(description = "获得连接工厂")
    public RedisConnectionFactory getConnectionFactory() {
        return redisTemplate.getConnectionFactory();
    }
}
