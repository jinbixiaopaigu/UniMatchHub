package cn.zwz.basics.baseVo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


//@Operation(description = "临时用户类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenUser implements Serializable{

    private static final long serialVersionUID = 1L;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "拥有的菜单权限")
    private List<String> permissions;

    @Schema(description = "是否自动登录")
    private Boolean saveLogin;
}
