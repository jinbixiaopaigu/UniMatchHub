package cn.zwz.bd.controller;

import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.data.utils.ZwzNullUtils;
import cn.zwz.bd.entity.Member;
import cn.zwz.bd.service.IMemberService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 郑为中
 * CSDN: Designer 小郑
 */
@Slf4j
@RestController
@Api(tags = "会员管理接口")
@RequestMapping("/zwz/member")
@Transactional
public class MemberController {

    @Autowired
    private IMemberService iMemberService;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条会员")
    public Result<Member> get(@RequestParam String id){
        return new ResultUtil<Member>().setData(iMemberService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部会员个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iMemberService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部会员")
    public Result<List<Member>> getAll(){
        return new ResultUtil<List<Member>>().setData(iMemberService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询会员")
    public Result<IPage<Member>> getByPage(@ModelAttribute Member member ,@ModelAttribute PageVo page){
        QueryWrapper<Member> qw = new QueryWrapper<>();
        if(!ZwzNullUtils.isNull(member.getName())) {
            qw.like("name",member.getName());
        }
        if(!ZwzNullUtils.isNull(member.getResidence())) {
            qw.like("residence",member.getResidence());
        }
        if(!ZwzNullUtils.isNull(member.getAddress())) {
            qw.like("address",member.getAddress());
        }
        if(!ZwzNullUtils.isNull(member.getHouse())) {
            qw.like("house",member.getHouse());
        }
        if(!ZwzNullUtils.isNull(member.getSchooling())) {
            qw.like("schooling",member.getSchooling());
        }
        IPage<Member> data = iMemberService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<Member>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改会员")
    public Result<Member> saveOrUpdate(Member member){
        if(iMemberService.saveOrUpdate(member)){
            return new ResultUtil<Member>().setData(member);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增会员")
    public Result<Member> insert(Member member){
        iMemberService.saveOrUpdate(member);
        return new ResultUtil<Member>().setData(member);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑会员")
    public Result<Member> update(Member member){
        iMemberService.saveOrUpdate(member);
        return new ResultUtil<Member>().setData(member);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除会员")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iMemberService.removeById(id);
        }
        return ResultUtil.success();
    }
}
