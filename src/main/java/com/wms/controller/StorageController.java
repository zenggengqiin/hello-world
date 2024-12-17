package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
import com.wms.entity.Goodstype;
import com.wms.entity.Storage;
import com.wms.entity.User;
import com.wms.service.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2024-01-29
 */
@RestController
@RequestMapping("/storage")
public class StorageController {

    @Autowired
    IStorageService iStorageService;

    //添加用户
    @PostMapping("/add")
    Result add(@RequestBody Storage storage){
        return iStorageService.save(storage)?Result.success():Result.fail();
    }
    //删除用户
    @GetMapping("/del")
    Result del(@RequestParam String id){
        return iStorageService.removeById(id)?Result.success():Result.fail();
    }
    //修改用户
    @PostMapping("/mod")
    Result mod(@RequestBody Storage storage){
        List<Storage> s = iStorageService.list(new QueryWrapper<Storage>().eq("name",storage.getName()));
        System.out.println(s.size()+"s.size()+++++++++++++++++++++++++++++++++");
        if(s.size()>0&&!s.get(0).getName().equals(iStorageService.getById(storage.getId()).getName())){
            return Result.fail();
        }else{
            return iStorageService.updateById(storage)?Result.success():Result.fail();
        }
    }
    //查找用户（模糊搜索 匹配)
    @PostMapping("/getPage")
    public Result getPage(@RequestBody QueryPageParam queryPageParam){
        Page<Storage> page = new Page<>();
        page.setCurrent(queryPageParam.getPageNum());
        page.setSize(queryPageParam.getPageSize());

        LambdaQueryWrapper<Storage> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(!"".equals(queryPageParam.getParam().get("name"))){
            lambdaQueryWrapper.like(Storage::getName,queryPageParam.getParam().get("name"));
        }


        IPage result = iStorageService.page(page,lambdaQueryWrapper);
        System.out.println("total="+result.getTotal());
        System.out.println("Records="+result.getRecords());
        return Result.success(result.getRecords(),result.getTotal());
    }

    @GetMapping("/findByName")
    public Result findByNum(@RequestParam String name){
        List list = iStorageService.lambdaQuery().eq(Storage::getName,name).list();
        return list.size()>0?Result.success(list):Result.fail();
    }

    @GetMapping("/list")
    public Result list(){
        List list = iStorageService.list();
        return Result.success(list);
    }

}
