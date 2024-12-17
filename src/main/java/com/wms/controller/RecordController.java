package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
import com.wms.entity.Analysis;
import com.wms.entity.Goods;
import com.wms.entity.Record;
import com.wms.service.IAnalysisService;
import com.wms.service.IGoodsService;
import com.wms.service.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Wrapper;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2024-01-30
 */
@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    IRecordService iRecordService;

    @Autowired
    IGoodsService iGoodsService;

    @Autowired
    IAnalysisService iAnalysisService;


    @PostMapping("/getPage")
    public Result getPage(@RequestBody QueryPageParam queryPageParam){
        Page<Record> page = new Page<>();
        page.setCurrent(queryPageParam.getPageNum());
        page.setSize(queryPageParam.getPageSize());

        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply(" a.goods=b.id and b.storage=c.id and b.goodstype=d.id ");
        if(!"".equals(queryPageParam.getParam().get("name"))){
            //lambdaQueryWrapper.like(Goods::getName,queryPageParam.getParam().get("name"));
            queryWrapper.like("b.name",queryPageParam.getParam().get("name"));
        }
        if(!"".equals(queryPageParam.getParam().get("goodstype"))){
            //lambdaQueryWrapper.eq(Goods::getGoodstype,queryPageParam.getParam().get("goodstype"));
            queryWrapper.eq("d.id",queryPageParam.getParam().get("goodstype"));
        }
        if(!"".equals(queryPageParam.getParam().get("storage"))){
            //lambdaQueryWrapper.eq(Goods::getStorage,queryPageParam.getParam().get("storage"));
            queryWrapper.eq("c.id",queryPageParam.getParam().get("storage"));
        }
        if(!"".equals(queryPageParam.getParam().get("date"))){
            System.out.println("date=========="+queryPageParam.getParam().get("date"));
            queryWrapper.like("a.createtime",queryPageParam.getParam().get("date"));
        }
//        if((Integer)queryPageParam.getParam().get("grade")==2){
//            queryWrapper.eq("a.userid",queryPageParam.getParam().get("id"));
//        }
        queryWrapper.orderByDesc("id");
        IPage result = iRecordService.pageR(page, queryWrapper);
        return Result.success(result.getRecords(),result.getTotal());
    }


    @PostMapping("/insave")
    Result in(@RequestBody Record record){
        Goods goods = iGoodsService.getById(record.getGoods());
        int n = goods.getCount()+record.getCount();
        goods.setCount(n);
        //库存更新
        iGoodsService.updateById(goods);


        //数据分析添加记录并
        QueryWrapper<Analysis> queryWrapper = new QueryWrapper<>();
        //goodsid
        queryWrapper.eq("goodsid",record.getGoods());
        // 获取当前日期
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(dateFormatter);
        queryWrapper.eq("date",formattedDate);
        queryWrapper.eq("inandout",1);
        Analysis analysis = iAnalysisService.getOne(queryWrapper);
        if(analysis!=null){
            analysis.setValue(analysis.getValue()+record.getCount());
            iAnalysisService.updateById(analysis);
        }else{
            analysis = new Analysis();
            analysis.setGoodsid(record.getGoods());
            analysis.setName(iGoodsService.getById(record.getGoods()).getName());
            analysis.setValue(record.getCount());
            analysis.setDate(formattedDate);
            analysis.setInandout(1);
            iAnalysisService.save(analysis);
        }


        return iRecordService.save(record)?Result.success():Result.fail();
    }

    @PostMapping("/outsave")
    Result out(@RequestBody Record record){
        Goods goods = iGoodsService.getById(record.getGoods());
        int n = goods.getCount()-record.getCount();
        goods.setCount(n);
        iGoodsService.updateById(goods);

        //数据分析添加记录并
        QueryWrapper<Analysis> queryWrapper = new QueryWrapper<>();
        //goodsid
        queryWrapper.eq("goodsid",record.getGoods());
        // 获取当前日期
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(dateFormatter);
        queryWrapper.eq("date",formattedDate);
        queryWrapper.eq("inandout",0);
        Analysis analysis = iAnalysisService.getOne(queryWrapper);
        if(analysis!=null){
            analysis.setValue(analysis.getValue()+record.getCount());
            iAnalysisService.updateById(analysis);
        }else{
            analysis = new Analysis();
            analysis.setGoodsid(record.getGoods());
            analysis.setName(iGoodsService.getById(record.getGoods()).getName());
            analysis.setValue(record.getCount());
            analysis.setInandout(0);
            analysis.setDate(formattedDate);
            iAnalysisService.save(analysis);
        }


        record.setCount(-record.getCount());
        return iRecordService.save(record)?Result.success():Result.fail();
    }

}
