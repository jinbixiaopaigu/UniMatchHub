package cn.zwz.data.entity;

import cn.zwz.basics.baseClass.ZwzBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;


@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "a_dict")
@TableName("a_dict")
@Schema(description = "数据字典")
public class Dict extends ZwzBaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "数据字典标题")
    private String title;

    @Schema(description = "数据字典排序值")
    @Column(precision = 10, scale = 2)
    private BigDecimal sortOrder;

    @Schema(description = "数据字典备注")
    private String description;

    @Schema(description = "数据字典类型")
    private String type;
}
