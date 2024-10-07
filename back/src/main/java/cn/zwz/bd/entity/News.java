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
@Table(name = "a_news")
@TableName("a_news")
@Schema(name = "新闻")
public class News extends ZwzBaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "新闻标题")
    private String title;

    @Schema(description = "新闻内容")
    private String content;

    @Schema(description = "发布状态")
    private String status;

    @Schema(description = "图片")
    private String image;

    @Schema(description = "排序值")
    private BigDecimal sortOrder;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "发布人")
    private String userName;
}