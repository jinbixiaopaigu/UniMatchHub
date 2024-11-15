package cn.zwz.basics.parameter;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;


//@Operation(description = "系统基础常量")
public interface CommonConstant {

    @Schema(description = "正常")
    Integer USER_STATUS_NORMAL = 0;

    @Schema(description = "禁用")
    Integer USER_STATUS_LOCK = -1;

    @Schema(description = "顶级菜单")
    Integer PERMISSION_NAV = -1;

    @Schema(description = "普通菜单")
    Integer PERMISSION_PAGE = 0;

    @Schema(description = "按钮菜单")
    Integer PERMISSION_OPERATION = 1;

    @Schema(description = "顶级菜单")
    Integer LEVEL_ZERO = 0;

    @Schema(description = "1级菜单")
    Integer LEVEL_ONE = 1;

    @Schema(description = "2级菜单")
    Integer LEVEL_TWO = 2;

    @Schema(description = "3级菜单")
    Integer LEVEL_THREE = 3;

    @Schema(description = "总部门ID")
    String PARENT_ID = "0";

    @Schema(description = "头像URL")
    String USER_DEFAULT_AVATAR = "https://raw.githubusercontent.com/jinbixiaopaigu/jinbixiaopaigu/refs/heads/main/test.png";
}
