package com.wms.controller;


import com.wms.common.Result;
import com.wms.entity.Menu;
import com.wms.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2024-01-27
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private IMenuService iMenuService;

//    @GetMapping("/list")
//    public Result list(@RequestParam String grade){
//        List list = iMenuService.lambdaQuery().like(Menu::getMenuRight,grade).list();
//        return Result.success(list);
//    }

}
