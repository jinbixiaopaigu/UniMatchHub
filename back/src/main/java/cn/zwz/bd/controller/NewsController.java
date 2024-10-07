package cn.zwz.bd.controller;

import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.basics.utils.SecurityUtil;
import cn.zwz.data.entity.User;
import cn.zwz.data.utils.ZwzNullUtils;
import cn.zwz.bd.entity.News;
import cn.zwz.bd.service.INewsService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;


@Slf4j
@RestController
@Tag(name = "新闻管理接口")
@RequestMapping("/zwz/news")
@Transactional
public class NewsController {

    @Autowired
    private INewsService iNewsService;

    @Autowired
    private SecurityUtil securityUtil;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @Operation(description = "查询单条新闻")
    public Result<News> get(@RequestParam String id){
        return new ResultUtil<News>().setData(iNewsService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @Operation(description = "查询全部新闻个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iNewsService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @Operation(description = "查询全部新闻")
    public Result<List<News>> getAll(){
        return new ResultUtil<List<News>>().setData(iNewsService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @Operation(description = "查询新闻")
    public Result<IPage<News>> getByPage(@ModelAttribute News news ,@ModelAttribute PageVo page){
        QueryWrapper<News> qw = new QueryWrapper<>();
        if(!ZwzNullUtils.isNull(news.getTitle())) {
            qw.like("title",news.getTitle());
        }
        if(!ZwzNullUtils.isNull(news.getContent())) {
            qw.like("content",news.getContent());
        }
        if(!ZwzNullUtils.isNull(news.getUserName())) {
            qw.like("user_name",news.getUserName());
        }
        IPage<News> data = iNewsService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<News>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @Operation(description = "增改新闻")
    public Result<News> saveOrUpdate(News news){
        if(iNewsService.saveOrUpdate(news)){
            return new ResultUtil<News>().setData(news);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @Operation(description = "新增新闻")
    public Result<News> insert(News news){
        if(Objects.equals(0,news.getSortOrder().compareTo(BigDecimal.ZERO))) {
            news.setSortOrder(BigDecimal.valueOf(iNewsService.count() + 1L));
        }
        User currUser = securityUtil.getCurrUser();
        news.setUserName(currUser.getNickname());
        iNewsService.saveOrUpdate(news);
        return new ResultUtil<News>().setData(news);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @Operation(description = "编辑新闻")
    public Result<News> update(News news){
        iNewsService.saveOrUpdate(news);
        return new ResultUtil<News>().setData(news);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @Operation(description = "删除新闻")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iNewsService.removeById(id);
        }
        return ResultUtil.success();
    }
}
