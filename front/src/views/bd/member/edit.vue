<template>
<div>
    <Card>
        <div slot="title">
            <div class="edit-head">
                <a @click="close" class="back-title">
                    <Icon type="ios-arrow-back" />返回
                </a>
                <div class="head-name">编辑会员</div>
                <span></span>
                <a @click="close" class="window-close">
                    <Icon type="ios-close" size="31" class="ivu-icon-ios-close" />
                </a>
            </div>
        </div>
        <Form ref="form" :model="form" :label-width="100" :rules="formValidate" label-position="left">
            <Row :gutter="16">
                <Col span="12">
                <FormItem label="会员姓名" prop="name">
                    <Input v-model="form.name" clearable show-word-limit maxlength="240" placeholder="请输入会员姓名..." style="width:100%" />
                </FormItem>
                </Col>
                <Col span="12">
                <FormItem label="联系方式" prop="mobile">
                    <Input v-model="form.mobile" clearable show-word-limit maxlength="240" placeholder="请输入联系方式..." style="width:100%" />
                </FormItem>
                </Col>
                <Col span="12">
                <FormItem label="照片" prop="image">
                    <upload-pic-input v-model="form.image" placeholder="请上传照片..." style="width:100%"></upload-pic-input>
                </FormItem>
                </Col>
                <Col span="12">
                <FormItem label="出生年月和星座" prop="birth">
                    <Input v-model="form.birth" clearable show-word-limit maxlength="240" placeholder="请输入出生年月和星座..." style="width:100%" />
                </FormItem>
                </Col>
                <Col span="12">
                <FormItem label="身高体重" prop="stature">
                    <Input v-model="form.stature" clearable type="textarea" :rows="2" show-word-limit maxlength="240" placeholder="请输入身高体重..." style="width:100%" />
                </FormItem>
                </Col>
                <Col span="12">
                <FormItem label="户籍地" prop="residence">
                    <Input v-model="form.residence" clearable type="textarea" :rows="2" show-word-limit maxlength="240" placeholder="请输入户籍地..." style="width:100%" />
                </FormItem>
                </Col>
                <Col span="12">
                <FormItem label="现居地" prop="address">
                    <Input v-model="form.address" clearable type="textarea" :rows="2" show-word-limit maxlength="240" placeholder="请输入现居地..." style="width:100%" />
                </FormItem>
                </Col>
                <Col span="12">
                <FormItem label="学历" prop="schooling">
                    <Select v-model="form.schooling" clearable placeholder="请选择学历..." style="width:100%">
                        <Option value="博士">博士</Option>
                        <Option value="硕士">硕士</Option>
                        <Option value="本科">本科</Option>
                        <Option value="专科">专科</Option>
                        <Option value="高中及以下">高中及以下</Option>
                    </Select>
                </FormItem>
                </Col>
                <Col span="12">
                <FormItem label="职业" prop="job">
                    <Input v-model="form.job" clearable type="textarea" :rows="2" show-word-limit maxlength="240" placeholder="请输入职业..." style="width:100%" />
                </FormItem>
                </Col>
                <Col span="12">
                <FormItem label="月均收入" prop="income">
                    <Input v-model="form.income" clearable type="textarea" :rows="2" show-word-limit maxlength="240" placeholder="请输入月均收入..." style="width:100%" />
                </FormItem>
                </Col>
                <Col span="12">
                <FormItem label="车房情况" prop="house">
                    <Select v-model="form.house" clearable placeholder="请选择车房情况..." style="width:100%">
                        <Option value="有车有房">有车有房</Option>
                        <Option value="有车无房">有车无房</Option>
                        <Option value="无车有房">无车有房</Option>
                        <Option value="无车无房">无车无房</Option>
                    </Select>
                </FormItem>
                </Col>
                <Col span="12">
                <FormItem label="有无婚史" prop="marry">
                    <Input v-model="form.marry" clearable type="textarea" :rows="2" show-word-limit maxlength="240" placeholder="请输入有无婚史..." style="width:100%" />
                </FormItem>
                </Col>
                <Col span="12">
                <FormItem label="家庭成员" prop="familyMember">
                    <Input v-model="form.familyMember" clearable type="textarea" :rows="2" show-word-limit maxlength="240" placeholder="请输入家庭成员..." style="width:100%" />
                </FormItem>
                </Col>
                <Col span="12">
                <FormItem label="自我介绍" prop="content">
                    <Input v-model="form.content" clearable type="textarea" :rows="2" show-word-limit maxlength="240" placeholder="请输入自我介绍..." style="width:100%" />
                </FormItem>
                </Col>
                <Col span="12">
                <FormItem label="择偶标准" prop="mateSelection">
                    <Input v-model="form.mateSelection" clearable type="textarea" :rows="2" show-word-limit maxlength="240" placeholder="请输入择偶标准..." style="width:100%" />
                </FormItem>
                </Col>
                <Col span="12">
                <FormItem label="备注" prop="remark">
                    <Input v-model="form.remark" clearable type="textarea" :rows="2" show-word-limit maxlength="240" placeholder="选填备注信息..." style="width:100%" />
                </FormItem>
                </Col>
                <Col span="24">
                <Form-item class="br">
                    <Button @click="handleSubmit" :loading="submitLoading" type="primary">提交并保存</Button>
                    <Button @click="handleReset">重置</Button>
                    <Button type="dashed" @click="close">关闭</Button>
                </Form-item>
                </Col>
            </Row>
        </Form>
    </Card>
</div>
</template>

<script>
import {
    editMember
} from "./api.js";
import uploadPicInput from "@/views/template/upload-pic-input";
export default {
    name: "edit",
    components: {
        uploadPicInput,
    },
    props: {
        data: Object
    },
    data() {
        return {
            submitLoading: false, // 表单提交状态
            form: { // 添加或编辑表单对象初始化数据
                name: "",
                mobile: "",
                image: "",
                birth: "",
                stature: "",
                residence: "",
                address: "",
                schooling: "",
                job: "",
                income: "",
                house: "",
                marry: "",
                familyMember: "",
                content: "",
                mateSelection: "",
                remark: "",
            },
            // 表单验证规则
            formValidate: {}
        };
    },
    methods: {
        init() {
            this.handleReset();
            this.form = this.data;
        },
        handleReset() {
            this.$refs.form.resetFields();
        },
        handleSubmit() {
            this.$refs.form.validate(valid => {
                if (valid) {
                    editMember(this.form).then(res => {
                        this.submitLoading = false;
                        if (res.success) {
                            this.$Message.success("操作成功");
                            this.submited();
                        }
                    });
                }
            });
        },
        close() {
            this.$emit("close", true);
        },
        submited() {
            this.$emit("submited", true);
        }
    },
    mounted() {
        this.init();
    }
};
</script>

<style lang="less">
// 建议引入通用样式 具体路径自行修改 可删除下面样式代码
// @import "../../../styles/single-common.less";
.edit-head {
    display: flex;
    align-items: center;
    justify-content: space-between;
    position: relative;

    .back-title {
        color: #515a6e;
        display: flex;
        align-items: center;
    }

    .head-name {
        display: inline-block;
        height: 20px;
        line-height: 20px;
        font-size: 16px;
        color: #17233d;
        font-weight: 500;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }

    .window-close {
        z-index: 1;
        font-size: 12px;
        position: absolute;
        right: 0px;
        top: -5px;
        overflow: hidden;
        cursor: pointer;

        .ivu-icon-ios-close {
            color: #999;
            transition: color .2s ease;
        }
    }

    .window-close .ivu-icon-ios-close:hover {
        color: #444;
    }
}
</style>
