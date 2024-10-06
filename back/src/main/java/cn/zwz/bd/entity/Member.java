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

/**
 * @author 郑为中
 * CSDN: Designer 小郑
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "a_member")
@TableName("a_member")
@ApiModel(value = "会员")
public class Member extends ZwzBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "会员姓名")
    private String name;

    @ApiModelProperty(value = "联系方式")
    private String mobile;

    @ApiModelProperty(value = "照片")
    private String image;

    @ApiModelProperty(value = "出生年月和星座")
    private String birth;

    @ApiModelProperty(value = "身高体重")
    private String stature;

    @ApiModelProperty(value = "户籍地")
    private String residence;

    @ApiModelProperty(value = "现居地")
    private String address;

    @ApiModelProperty(value = "学历")
    private String schooling;

    @ApiModelProperty(value = "职业")
    private String job;

    @ApiModelProperty(value = "月均收入")
    private String income;

    @ApiModelProperty(value = "车房情况")
    private String house;

    @ApiModelProperty(value = "有无婚史")
    private String marry;

    @ApiModelProperty(value = "家庭成员")
    private String familyMember;

    @ApiModelProperty(value = "自我介绍")
    private String content;

    @ApiModelProperty(value = "择偶标准")
    private String mateSelection;

    @ApiModelProperty(value = "备注")
    private String remark;
}