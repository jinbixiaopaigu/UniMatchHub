package cn.zwz.data.entity;

import cn.zwz.basics.baseClass.ZwzBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.util.List;


@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "a_role")
@TableName("a_role")
@Schema(name = "角色")
public class Role extends ZwzBaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "角色名称")
    private String name;

    @Schema(description = "数据权限")
    private int dataType;

    @Schema(description = "是否默认")
    private Boolean defaultRole;

    @Schema(description = "角色备注")
    private String description;

    @Transient
    @TableField(exist=false)
    @Schema(description = "角色拥有菜单列表")
    private List<RolePermission> permissions;
}
