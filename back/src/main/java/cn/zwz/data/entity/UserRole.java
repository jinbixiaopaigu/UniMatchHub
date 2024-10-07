package cn.zwz.data.entity;

import cn.zwz.basics.baseClass.ZwzBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;


@Data
@Accessors(chain = true)
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "a_user_role")
@TableName("a_user_role")
@Schema(name = "用户角色")
public class UserRole extends ZwzBaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "角色ID")
    private String roleId;

    @Schema(description = "用户ID")
    private String userId;

    @Transient
    @TableField(exist=false)
    @Schema(description = "用户名")
    private String userName;

    @Transient
    @TableField(exist=false)
    @Schema(description = "角色名")
    private String roleName;
}
