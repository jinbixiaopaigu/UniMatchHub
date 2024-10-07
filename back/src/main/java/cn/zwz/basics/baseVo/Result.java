package cn.zwz.basics.baseVo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "返回主数据，泛型类型")
    private T result;

    @Schema(description = "是否请求成功，true为请求成功，false为请求失败")
    private boolean success;

    @Schema(description = "返回状态代码，默认200为成功")
    private Integer code;

    @Schema(description = "时间戳，当前系统的时间戳")
    private long timestamp = System.currentTimeMillis();

    @Schema(description = "提示信息，额外的提示信息")
    private String message;
}
