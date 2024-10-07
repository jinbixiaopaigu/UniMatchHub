package cn.zwz.data.entity;

import cn.zwz.basics.baseClass.ZwzBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "a_setting")
@TableName("a_setting")
@Schema(name = "配置")
@NoArgsConstructor
public class Setting extends ZwzBaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "设置内容")
    private String value;

    public Setting(String id){
        super.setId(id);
    }
}