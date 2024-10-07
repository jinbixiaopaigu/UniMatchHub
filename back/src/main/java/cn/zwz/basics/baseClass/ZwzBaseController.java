package cn.zwz.basics.baseClass;

import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;


// @Operation(description = "模板控制器层")
public abstract class ZwzBaseController<E, ID extends Serializable> {

    private final ZwzBaseService<E, ID> zwzService;

    // 构造函数注入，代替字段注入
    public ZwzBaseController(ZwzBaseService<E, ID> zwzService) {
        this.zwzService = zwzService;
    }

    // 获取注入的服务类实例
    public ZwzBaseService<E, ID> getZwzService() {
        return zwzService;
    }

    @GetMapping(value = "/getOne")
    @ResponseBody
    @Operation(summary = "查询单个数据", description = "通过ID查询单个数据")
    public Result<E> getOne(@RequestParam ID id) {
        return new ResultUtil<E>().setData(zwzService.get(id));
    }

    @GetMapping(value = "/getAll")
    @ResponseBody
    @Operation(summary = "查询全部数据", description = "查询全部数据")
    public Result<List<E>> getAll() {
        return new ResultUtil<List<E>>().setData(zwzService.getAll());
    }

    @GetMapping(value = "/getByPage")
    @ResponseBody
    @Operation(summary = "查询数据", description = "分页查询数据")
    public Result<Page<E>> getByPage(PageVo page) {
        return new ResultUtil<Page<E>>().setData(zwzService.findAll(PageUtil.initPage(page)));
    }

    @PostMapping(value = "/save")
    @ResponseBody
    @Operation(summary = "新增数据", description = "新增一条数据")
    public Result<E> save(E entity) {
        return new ResultUtil<E>().setData(zwzService.save(entity));
    }

    @PutMapping(value = "/update")
    @ResponseBody
    @Operation(summary = "编辑数据", description = "编辑现有数据")
    public Result<E> update(E entity) {
        return new ResultUtil<E>().setData(zwzService.update(entity));
    }

    @PostMapping(value = "/count")
    @ResponseBody
    @Operation(summary = "查询数据条数", description = "查询数据总条数")
    public Result<Long> count() {
        return new ResultUtil<Long>().setData(zwzService.count());
    }

    @PostMapping(value = "/delOne")
    @ResponseBody
    @Operation(summary = "删除单条数据", description = "通过ID删除单条数据")
    public Result<Object> delByIds(@RequestParam ID id) {
        zwzService.delete(id);
        return new ResultUtil<Object>().setSuccessMsg("OK");
    }

    @PostMapping(value = "/delByIds")
    @ResponseBody
    @Operation(summary = "删除多条数据", description = "通过ID列表删除多条数据")
    public Result<Object> delByIds(@RequestParam ID[] ids) {
        for (ID id : ids) {
            zwzService.delete(id);
        }
        return new ResultUtil<Object>().setSuccessMsg("OK");
    }
}
