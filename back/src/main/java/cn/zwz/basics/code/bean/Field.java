package cn.zwz.basics.code.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;


//@Operation(description = "代码生成器基础类")
@Data
public class Field {

    @Schema(description = "字段名称")
    private String name;

    @Schema(description = "字段标识")
    private String field;

    @Schema(description = "字段层级")
    private String level;

    @Schema(description = "是否在表格显示")
    private Boolean tableShow;

    @Schema(description = "排序值")
    private BigDecimal sortOrder;

    @Schema(description = "是否可搜索")
    private Boolean searchable;

    @Schema(description = "是否可添加编辑")
    private Boolean editable;

    @Schema(description = "字段类型")
    private String type;

    @Schema(description = "搜索层级")
    private String searchLevel;

    @Schema(description = "搜索栏类型")
    private String searchType;

    @Schema(description = "默认排序规则")
    private String defaultSortType;

    @Schema(description = "字段")
    private Boolean validate;

    @Schema(description = "是否为默认排序")
    private Boolean defaultSort;

    @Schema(description = "是否可排序")
    private Boolean sortable;
}
