package cn.zwz.bd.entity;

import cn.zwz.basics.baseClass.ZwzBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "a_message")
@TableName("a_message")
@Schema(name = "留言")
public class Message extends ZwzBaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "留言内容")
    private String content;

    @Schema(description = "留言人ID")
    private String userId;

    @Schema(description = "留言人")
    private String userName;

    @Schema(description = "留言时间")
    private String time;

    @Schema(description = "回复内容")
    private String replyContent;

    @Schema(description = "回复人ID")
    private String replyId;

    @Schema(description = "回复人")
    private String replyName;

    @Schema(description = "回复时间")
    private String replyTime;
}