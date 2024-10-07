package cn.zwz.data.vo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;


@Tag(name = "缓存VO类")
@Data
@AllArgsConstructor
public class RedisVo {

    @Schema(description = "Redis键")
    private String key;

    @Schema(description = "Redis值")
    private String value;

    @Schema(description = "保存秒数")
    private Long expireTime;
}
