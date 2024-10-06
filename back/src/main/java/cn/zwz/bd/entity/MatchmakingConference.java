package cn.zwz.bd.entity;

import cn.zwz.basics.baseClass.ZwzBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;

/**
 * @author 郑为中
 * CSDN: Designer 小郑
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "a_matchmaking_conference")
@TableName("a_matchmaking_conference")
@ApiModel(value = "相亲大会")
public class MatchmakingConference extends ZwzBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "活动标题")
    private String title;

    @ApiModelProperty(value = "活动内容")
    private String content;

    @ApiModelProperty(value = "活动时间")
    private String time;

    @ApiModelProperty(value = "活动地点")
    private String address;

    @ApiModelProperty(value = "费用")
    private BigDecimal cost;

    @ApiModelProperty(value = "备注")
    private String remark;
}