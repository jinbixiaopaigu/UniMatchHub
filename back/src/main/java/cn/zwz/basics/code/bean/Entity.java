package cn.zwz.basics.code.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//@Operation(description = "代码生成器基础类")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entity {

    @Schema(description = "控制器层包")
    private String controllerPackage;

    @Schema(description = "服务层实现包")
    private String serviceImplPackage;

    @Schema(description = "服务层接口包")
    private String servicePackage;

    @Schema(description = "数据链路层包")
    private String daoPackage;

    @Schema(description = "实体VO包")
    private String entityPackage;

    @Schema(description = "类名")
    private String className;

    @Schema(description = "类名小写")
    private String classNameLowerCase;

    @Schema(description = "备注")
    private String description;

    @Schema(description = "作者")
    private String author;

    @Schema(description = "表名")
    private String tableName;

    @Schema(description = "主键数据类型")
    private String primaryKeyType;

    @Schema(description = "是否树状")
    private Boolean isTree;

    @Schema(description = "是否流程")
    private Boolean activiti;
}
