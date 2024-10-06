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
@Table(name = "a_news")
@TableName("a_news")
@ApiModel(value = "新闻")
public class News extends ZwzBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "新闻标题")
    private String title;

    @ApiModelProperty(value = "新闻内容")
    private String content;

    @ApiModelProperty(value = "发布状态")
    private String status;

    @ApiModelProperty(value = "图片")
    private String image;

    @ApiModelProperty(value = "排序值")
    private BigDecimal sortOrder;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "发布人")
    private String userName;
}