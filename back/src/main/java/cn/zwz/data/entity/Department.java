package cn.zwz.data.entity;

import cn.zwz.basics.baseClass.ZwzBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author 郑为中
 * CSDN: Designer 小郑
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "a_department")
@TableName("a_department")
@Schema(description = "部门")
public class Department extends ZwzBaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "部门标题")
    private String title;

    @Schema(description = "状态")
    private Integer status = 0;

    @Schema(description = "排序值")
    @Column(precision = 10, scale = 2)
    private BigDecimal sortOrder;

    @Schema(description = "父节点标识")
    private Boolean isParent = false;

    @Schema(description = "父部门ID")
    private String parentId;

    @Transient
    @TableField(exist=false)
    @Schema(description = "领导人")
    private List<String> mainHeader;

    @Transient
    @TableField(exist=false)
    @Schema(description = "副领导人")
    private List<String> viceHeader;

    @Transient
    @TableField(exist=false)
    @Schema(description = "父部门名称")
    private String parentTitle;
}
