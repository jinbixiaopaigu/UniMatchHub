package cn.zwz.bd.controller;

import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.data.utils.ZwzNullUtils;
import cn.zwz.bd.entity.MatchmakingConference;
import cn.zwz.bd.service.IMatchmakingConferenceService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@RestController
@Tag(name = "相亲大会管理接口")
@RequestMapping("/zwz/matchmakingConference")
@Transactional
public class MatchmakingConferenceController {

    @Autowired
    private IMatchmakingConferenceService iMatchmakingConferenceService;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @Operation(description = "查询单条相亲大会")
    public Result<MatchmakingConference> get(@RequestParam String id){
        return new ResultUtil<MatchmakingConference>().setData(iMatchmakingConferenceService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @Operation(description = "查询全部相亲大会个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iMatchmakingConferenceService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @Operation(description = "查询全部相亲大会")
    public Result<List<MatchmakingConference>> getAll(){
        return new ResultUtil<List<MatchmakingConference>>().setData(iMatchmakingConferenceService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @Operation(description = "查询相亲大会")
    public Result<IPage<MatchmakingConference>> getByPage(@ModelAttribute MatchmakingConference matchmakingConference ,@ModelAttribute PageVo page){
        QueryWrapper<MatchmakingConference> qw = new QueryWrapper<>();
        if(!ZwzNullUtils.isNull(matchmakingConference.getTitle())) {
            qw.like("title",matchmakingConference.getTitle());
        }
        if(!ZwzNullUtils.isNull(matchmakingConference.getContent())) {
            qw.like("content",matchmakingConference.getContent());
        }
        if(!ZwzNullUtils.isNull(matchmakingConference.getAddress())) {
            qw.like("address",matchmakingConference.getAddress());
        }
        IPage<MatchmakingConference> data = iMatchmakingConferenceService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<MatchmakingConference>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @Operation(description = "增改相亲大会")
    public Result<MatchmakingConference> saveOrUpdate(MatchmakingConference matchmakingConference){
        if(iMatchmakingConferenceService.saveOrUpdate(matchmakingConference)){
            return new ResultUtil<MatchmakingConference>().setData(matchmakingConference);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @Operation(description = "新增相亲大会")
    public Result<MatchmakingConference> insert(MatchmakingConference matchmakingConference){
        iMatchmakingConferenceService.saveOrUpdate(matchmakingConference);
        return new ResultUtil<MatchmakingConference>().setData(matchmakingConference);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @Operation(description = "编辑相亲大会")
    public Result<MatchmakingConference> update(MatchmakingConference matchmakingConference){
        iMatchmakingConferenceService.saveOrUpdate(matchmakingConference);
        return new ResultUtil<MatchmakingConference>().setData(matchmakingConference);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @Operation(description = "删除相亲大会")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iMatchmakingConferenceService.removeById(id);
        }
        return ResultUtil.success();
    }
}
