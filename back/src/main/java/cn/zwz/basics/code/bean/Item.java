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
public class Item {

    @Schema(description = "子项标题")
    private String title;

    @Schema(description = "子项类别")
    private String type;

    @Schema(description = "子项大写")
    private String upperName;

    @Schema(description = "子项小写")
    private String lowerName;

    @Schema(description = "子项行名")
    private String lineName;
}
