package cn.zwz.data.utils;

import cn.zwz.data.entity.Permission;
import cn.zwz.data.vo.MenuVo;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "菜单转换VO类")
public class VoUtil {

    @Operation(description = "菜单转换VO类转换")
    public static MenuVo permissionToMenuVo(Permission permission){
        MenuVo vo = new MenuVo();
        BeanUtil.copyProperties(permission, vo);
        return vo;
    }
}
