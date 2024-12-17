package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
import com.wms.entity.Goods;
import com.wms.entity.Goodstype;
import com.wms.mapper.AnalysisMapper;
import com.wms.service.IGoodsService;
import com.wms.service.IGoodstypeService;
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
@RequestMapping("/goods")
public class GoodsController {


    @Autowired
    IGoodsService iGoodsService;

    @Autowired
    AnalysisMapper analysisMapper;

    //添加用户
    @PostMapping("/add")
    Result add(@RequestBody Goods goods){
        return iGoodsService.save(goods)?Result.success():Result.fail();
    }
    //删除用户
    @GetMapping("/del")
    Result del(@RequestParam String id){
        return iGoodsService.removeById(id)?Result.success():Result.fail();
    }
    //修改用户
    @PostMapping("/mod")
    Result mod(@RequestBody Goods goods){
        List<Goods> s = iGoodsService.list(new QueryWrapper<Goods>().eq("name",goods.getName()));
        if(s.size()>0&&!s.get(0).getName().equals(iGoodsService.getById(goods.getId()).getName())){
            return Result.fail();
        }else{
            return iGoodsService.updateById(goods)?Result.success():Result.fail();
        }
    }
    //查找用户（模糊搜索 匹配)
    @PostMapping("/getPage")
    public Result getPage(@RequestBody QueryPageParam queryPageParam){
        Page<Goods> page = new Page<>();
        page.setCurrent(queryPageParam.getPageNum());
        page.setSize(queryPageParam.getPageSize());

        QueryWrapper<Goods> lambdaQueryWrapper = new QueryWrapper<>();
        if(!"".equals(queryPageParam.getParam().get("name"))){
            lambdaQueryWrapper.like("name",queryPageParam.getParam().get("name"));
        }
        if(!"".equals(queryPageParam.getParam().get("goodstype"))){
            lambdaQueryWrapper.eq("goodstype",queryPageParam.getParam().get("goodstype"));

        }
        if(!"".equals(queryPageParam.getParam().get("storage"))){
            lambdaQueryWrapper.eq("storage",queryPageParam.getParam().get("storage"));
        }
//        lambdaQueryWrapper.orderByAsc("count");

        IPage result = iGoodsService.page(page,lambdaQueryWrapper);
        System.out.println("total="+result.getTotal());
        System.out.println("Records="+result.getRecords());

        //查询七日内平均销量
        for(int i=0;i<result.getRecords().size();i++){
            Goods goods = (Goods) result.getRecords().get(i);
            if(analysisMapper.getEstimate(goods.getId().toString()).length<14){
                goods.setRemark("");
                result.getRecords().set(i,goods);
            }else if(goods.getCount()<=estimate(goods.getId().toString())){
                goods.setRemark("库存小于预计7日库存:"+estimate(goods.getId().toString())+"请及时补货 ! ! !");
                result.getRecords().set(i,goods);
            }else{
                goods.setRemark("预计未来7日最小库存:"+estimate(goods.getId().toString()));
                result.getRecords().set(i,goods);
            }

        }

        return Result.success(result.getRecords(),result.getTotal());
    }

    @GetMapping("/findByName")
    public Result findByNum(@RequestParam String name){
        List list = iGoodsService.lambdaQuery().eq(Goods::getName,name).list();
        return list.size()>0?Result.success(list):Result.fail();
    }

    //库存预计算法
    public Integer estimate(String id){
        Double[] i = analysisMapper.getEstimate(id);
        Double now=0.00;
        for(int x=0;x<7;x++){
            now = now+i[x];
        }
        Double lest = 0.00;
        for(int x=7;x<14;x++){
            lest = lest+i[x];
        }
        Double growth =(now-lest)/lest;
        Double estimate = now*(1+growth);
        int i1 = estimate.intValue();
        return Integer.valueOf(i1);
    }


}
