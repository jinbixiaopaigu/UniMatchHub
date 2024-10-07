package cn.zwz.data.entity;

import cn.zwz.basics.baseClass.ZwzBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;


@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "a_log")
@TableName("a_log")
@Schema(name = "日志")
public class Log extends ZwzBaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "日志标题")
    private String name;

    @Schema(description = "日志类型")
    private Integer logType;

    @Schema(description = "日志代码")
    private String code;

    @Schema(description = "设备")
    private String device;

    @Schema(description = "请求URL")
    private String requestUrl;

    @Schema(description = "请求方式")
    private String requestType;

    @Column(columnDefinition ="TEXT")
    @Schema(description = "参数")
    private String requestParam;

    @Schema(description = "触发者")
    private String username;

    @Schema(description = "IP地址")
    private String ip;

    @Schema(description = "IP定位")
    private String ipInfo;

    @Schema(description = "消耗毫秒数")
    private Integer costTime;

    @Transient
    @TableField(exist=false)
    @Schema(description = "搜索开始时间")
    private String startDate;

    @Transient
    @TableField(exist=false)
    @Schema(description = "搜索结束时间")
    private String endDate;
}
