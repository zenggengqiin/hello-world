package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
import com.wms.entity.User;
import com.wms.service.IUserService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2024-01-22
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    IUserService iUserService;

    //添加用户
    @PostMapping("/add")
    Result add(@RequestBody User user){
        return iUserService.save(user)?Result.success():Result.fail();
    }
    //删除用户
    @GetMapping("/del")
    Result del(@RequestParam String id){
        return iUserService.removeById(id)?Result.success():Result.fail();
    }
    //修改用户
    @PostMapping("/mod")
    Result mod(@RequestBody User user){
        return iUserService.updateById(user)?Result.success():Result.fail();
    }
    //查找用户（模糊搜索 匹配)
    @PostMapping("/get")
    public List<User> get(@RequestBody User user){
        return iUserService.get(user);
    }

    @PostMapping("/getPage")
    public Result getPage(@RequestBody QueryPageParam queryPageParam){
        Page<User> page = new Page<>();
        page.setCurrent(queryPageParam.getPageNum());
        page.setSize(queryPageParam.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(!"".equals(queryPageParam.getParam().get("name"))){
            lambdaQueryWrapper.like(User::getName,queryPageParam.getParam().get("name"));
        }
        if(!"".equals(queryPageParam.getParam().get("grade"))){
            lambdaQueryWrapper.eq(User::getGrade,queryPageParam.getParam().get("grade"));
        }

        IPage result = iUserService.page(page,lambdaQueryWrapper);
        System.out.println("total="+result.getTotal());

        return Result.success(result.getRecords(),result.getTotal());
    }

    @GetMapping("/getAll")
    public Result getAll(){
        return Result.success(iUserService.getAll());
    }

    @GetMapping("/findByNum")
    public Result findByNum(@RequestParam String num){
        List list = iUserService.lambdaQuery().eq(User::getNum,num).list();
        return list.size()>0?Result.success(list):Result.fail();
    }

    @GetMapping("/login")
    public Result login(@RequestParam("num") String num,@RequestParam("password") String password){
        return iUserService.login(num,password);
    }

}
