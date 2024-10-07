package cn.zwz.basics.code;

import cn.zwz.basics.code.vue.MyBatisPlusUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;


@Slf4j
//@Operation(description = "后端代码生成器执行类")
public class MyBatisPlusCodeUtils {

    @Operation(description = "一键产生增删改查代码")
    public static void main(String[] args) throws Exception {
        new MyBatisPlusUtils(CLASS_NAME,DESCRIPTION,CLASS_PATH,REMOVE_FLAG).work();
    }

    @Schema(description = "类名")
    private static final String CLASS_NAME = "DynamicTableData";

    @Schema(description = "类备注")
    private static final String DESCRIPTION = "动态表格值";

    @Schema(description = "生成路径")
    private static final String CLASS_PATH = "cn.zwz.test";

    @Schema(description = "是否删除代码")
    private static final Boolean REMOVE_FLAG = false;
}
