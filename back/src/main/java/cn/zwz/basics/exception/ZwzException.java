package cn.zwz.basics.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


//@Operation(description = "自定义异常")
@Data
public class ZwzException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private static final String DEFAULT_MSG = "系统出现错误";

    @Schema(description = "异常消息内容")
    private String msg;

    public ZwzException(){
        super(DEFAULT_MSG);
        this.msg = DEFAULT_MSG;
    }

    public ZwzException(String msg){
        super(msg);
        this.msg = msg;
    }
}
