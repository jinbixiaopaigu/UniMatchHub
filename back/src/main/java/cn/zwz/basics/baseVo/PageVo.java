package cn.zwz.basics.baseVo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "排序字段名")
    private String sort;

    @Schema(description = "页码编号，即展示第几页")
    private int pageNumber;

    @Schema(description = "排序类型，升序为asc，降序为desc")
    private String order;

    @Schema(description = "每页展示的个数，建议设置为15")
    private int pageSize;
}
