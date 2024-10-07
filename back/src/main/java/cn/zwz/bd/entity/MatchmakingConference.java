package cn.zwz.bd.entity;

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
@Table(name = "a_matchmaking_conference")
@TableName("a_matchmaking_conference")
@Schema(name = "相亲大会")
public class MatchmakingConference extends ZwzBaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "活动标题")
    private String title;

    @Schema(description = "活动内容")
    private String content;

    @Schema(description = "活动时间")
    private String time;

    @Schema(description = "活动地点")
    private String address;

    @Schema(description = "费用")
    private BigDecimal cost;

    @Schema(description = "备注")
    private String remark;
}