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
@Table(name = "a_member")
@TableName("a_member")
@Schema(name = "会员")
public class Member extends ZwzBaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "会员姓名")
    private String name;

    @Schema(description = "联系方式")
    private String mobile;

    @Schema(description = "照片")
    private String image;

    @Schema(description = "出生年月和星座")
    private String birth;

    @Schema(description = "身高体重")
    private String stature;

    @Schema(description = "户籍地")
    private String residence;

    @Schema(description = "现居地")
    private String address;

    @Schema(description = "学历")
    private String schooling;

    @Schema(description = "职业")
    private String job;

    @Schema(description = "月均收入")
    private String income;

    @Schema(description = "车房情况")
    private String house;

    @Schema(description = "有无婚史")
    private String marry;

    @Schema(description = "家庭成员")
    private String familyMember;

    @Schema(description = "自我介绍")
    private String content;

    @Schema(description = "择偶标准")
    private String mateSelection;

    @Schema(description = "备注")
    private String remark;
}