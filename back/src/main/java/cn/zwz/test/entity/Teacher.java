package cn.zwz.test.entity;

import cn.zwz.basics.baseClass.ZwzBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;


@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "a_teacher")
@TableName("a_teacher")
@Schema(name = "教师")
public class Teacher extends ZwzBaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "学历")
    private String education;

    @Schema(description = "年龄")
    private BigDecimal age;

    @Schema(description = "毕业院校")
    private String graduated;

    @Schema(description = "工资")
    private BigDecimal wages;

    @Schema(description = "在职状态")
    private String status;

    @Schema(description = "签名")
    private String autograph;

    @Schema(description = "个人简历")
    private String resume;

    @Schema(description = "备注")
    private String remark;
}