package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wms.common.Result;
import com.wms.entity.*;
import com.wms.mapper.AnalysisMapper;
import com.wms.mapper.GoodsMapper;
import com.wms.service.IAnalysisService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2024-02-03
 */
@RestController
@RequestMapping("/analysis")
public class AnalysisController {

    @Autowired
    AnalysisMapper analysisMapper;

    @Autowired
    IAnalysisService iAnalysisService;

    @Autowired
    GoodsMapper goodsMapper;


    @GetMapping("/getsix")
    public Result getsix(String goodstype){
        return Result.success(analysisMapper.getsix(goodstype));
    }

    @GetMapping("/getOut")
    public Result getOut(String goodsid){
        return Result.success(analysisMapper.getOut(goodsid));
    }

//    @GetMapping("getFive")
//    public Result getFive(){
//        List<AnalysisRes> analysisRes = new ArrayList<>();
//        //获取前5
//        List<Analysis> list = analysisMapper.getfiveID();
//        //循环获取每个id前14条数据
//
//        for(int i=0;i<list.size();i++){
//            List<String> date = analysisMapper.getdate(list.get(i).getGoodsid());
//            //拿到第一个id获取14个数据
//            Double [] doubles = analysisMapper.getmore(list.get(i).getGoodsid());
//            String [] strings = new String[7];
//            for(int j=0;j<7;j++){
//                //计算环比
//                strings[j]=String.format("%.4f", (doubles[7+j]-doubles[j])/doubles[j]);
//            }
//            //封装数据
//            analysisRes.add(new AnalysisRes(list.get(i).getName(),strings,date.get(i)));
//        }
//        return Result.success(analysisRes);
//    }

    @GetMapping("/getOutThe")
    public Result getOut1(String goodsname){
        if(goodsname!=null&&!goodsname.equals("")){
            Goods goods = goodsMapper.selectOne(new QueryWrapper<Goods>().eq("name",goodsname));
            if(goods!=null){
                return Result.success(analysisMapper.getOut(goods.getId().toString()));
            }else{
                return Result.fail();
            }

        }else{
            return Result.fail();
        }
    }

    @GetMapping("/getOneSeven")
    public Result getOneSeven(String goodsname){
        if(goodsname!=null&&!goodsname.equals("")){
            Goods goods = goodsMapper.selectOne(new QueryWrapper<Goods>().eq("name",goodsname));
            if(goods!=null){
                Analysis analysis = new Analysis();
                Double [] doubles = analysisMapper.getmore(goods.getId());
                String [] strings = new String[7];
                for(int j=0;j<7;j++){
                    //计算环比
                    strings[j]=String.format("%.4f", (doubles[7+j]-doubles[j])/doubles[j]);
                }
                //封装数据
                AnalysisRes analysisRes = new AnalysisRes(goods.getName(),strings,"null");
                System.out.println("AnalysisRes+++++++++++"+analysisRes);
                return Result.success(analysisRes);
            }else{
                return Result.fail();
            }

        }else{
            return Result.fail();
        }
    }

    @GetMapping("/getDaySum")
    public Result getDaySum(){
        System.out.println(analysisMapper.getDaySum()+"[]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]");
        if(analysisMapper.getDaySum()!=null){
            return Result.success(analysisMapper.getDaySum());
        }else{
            return Result.success(0);
        }

    }

    @GetMapping("/getDayMax")
    public Result getDayMax(){
        if(!iAnalysisService.getDayMax().equals("null")){
            return Result.success(iAnalysisService.getDayMax());
        }else{
            return Result.fail();
        }
    }

    @GetMapping("/getDayMin")
    public Result getDayMin(){
        if(!iAnalysisService.getDayMin().equals("null")){
            return Result.success(iAnalysisService.getDayMin());
        }else{
            return Result.fail();
        }
    }



    @GetMapping("/getTop")
    private Result getTop(){
        TopRes topRes = iAnalysisService.getTop();
        if(topRes!=null){
            String str;
            str = topRes.getName()+": "+topRes.getSum();
            System.out.println(str+"+++++++++++++++++++++++++++++++++");
            return Result.success(str);
        }else{
            return  Result.fail();
        }
    }

    @GetMapping("/zb")
    private Result zb(){
        QueryWrapper<Analysis> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply("DATE(date) = CURDATE()");
        if(iAnalysisService.getTop()!=null){
            queryWrapper.eq("goodsid",iAnalysisService.getTop().getGoodsid());
        }else{
            return Result.fail();
        }

        queryWrapper.eq("inandout","0");
        Analysis analysis = iAnalysisService.getOne(queryWrapper);
        if(iAnalysisService.getOne(queryWrapper)!=null){
            Double zb = new Double(analysis.getValue())/new Double(analysisMapper.getDaySum());
            String zzbb = String.format("%.2f", zb*100);
            return Result.success(zzbb);
        }else{
            return Result.success(0);
        }
    }

    @GetMapping("/tb")
    private Result tb(){
        QueryWrapper<Analysis> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.apply("DATE(date) = CURDATE()");
        if(iAnalysisService.getTop()!=null){
            queryWrapper1.eq("goodsid",iAnalysisService.getTop().getGoodsid());
        }else{
            return Result.fail();
        }

        queryWrapper1.eq("inandout","0");
        Analysis analysis1 = iAnalysisService.getOne(queryWrapper1);

        QueryWrapper<Analysis> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.apply("DATE(date) = DATE_SUB(CURDATE(), INTERVAL 1 DAY)");
        queryWrapper2.eq("goodsid",iAnalysisService.getTop().getGoodsid());
        queryWrapper2.eq("inandout","0");
        Analysis analysis2 = iAnalysisService.getOne(queryWrapper2);

        if(analysis1==null){
            return Result.success(0);
        }else if(analysis2==null){
            return Result.success(100);
        }
        else{
            Double tb;
            Double now = new Double(analysis1.getValue());
            Double yearsday = new Double(analysis2.getValue());
            tb = (now-yearsday)/now;
            String ttbb = String.format("%.2f", tb*100);
            System.out.println("++++++++++++++++++++++"+ttbb);
            return Result.success(ttbb);
        }
    }

    @GetMapping("/getSum")
    private Result getSum(){
        return Result.success(analysisMapper.getSum());
    }

    @GetMapping("getFive1")
    public Result getFive1(String goodstype){
        List<AnalysisRes> analysisRes = new ArrayList<>();
        //获取前6
        List<Analysis> list = analysisMapper.getfiveID(goodstype);
        //循环获取每个id前8条数据

        for(int i=0;i<list.size();i++){
            List<String> date = analysisMapper.getdate(list.get(i).getGoodsid());
            //拿到第一个id获取8个数据
            Double [] doubles = analysisMapper.getmore(list.get(i).getGoodsid());
            String [] strings = new String[8];
            if(doubles.length<8){
                continue;
            }
            for(int j=0;j<7;j++){
                //计算增长率
                strings[j]=String.format("%.4f", (doubles[j]-doubles[j+1])/doubles[j+1]);
            }
            //封装数据
            analysisRes.add(new AnalysisRes(list.get(i).getName(),strings,date.get(i)));
        }
        return Result.success(analysisRes);
    }

    @GetMapping("/getTomorrow")
    private Result getTomorrow(String goodstype){
        List<Analysis> analysis = new ArrayList<>();
        //获取前6
        List<Analysis> list = analysisMapper.getfiveID(goodstype);
        for(int i=0;i<list.size();i++){
            Double tomorrow;
            Double now;
            Double lest;
            Double [] doubles = analysisMapper.getTomorrow(list.get(i).getGoodsid());
            //计算前三
            if(doubles.length<6){
                continue;
            }
            now = doubles[0]+doubles[1]+doubles[2];
            lest = doubles[3]+doubles[4]+doubles[5];
            tomorrow = (now*(1+((now-lest)/lest)))/3;
            Analysis analysis1 = new Analysis();
            analysis1.setName(list.get(i).getName());
            analysis1.setValue(tomorrow.intValue());
            analysis.add(analysis1);
        }
        return Result.success(analysis);
    }

    @GetMapping("/getOne")
    public Result getOne(String goodsname){
        List<AnalysisRes> analyses = new ArrayList<>();
        if(goodsname!=null&&!goodsname.equals("")){
            //名称寻找id
            Goods goods = goodsMapper.selectOne(new QueryWrapper<Goods>().eq("name",goodsname));
            if(goods!=null){
                List<String> date = analysisMapper.getdate(goods.getId());
                //拿到第一个id获取8个数据
                Double [] doubles = analysisMapper.getmore(goods.getId());
                if(doubles.length<8){
                    return Result.fail();
                }
                for(int j=0;j<7;j++){
                    //计算增长率
                    String [] strings = new String[1];
                    strings[0]=String.format("%.4f", (doubles[j]-doubles[j+1])/doubles[j+1]);
                    AnalysisRes analysisRes = new AnalysisRes();
                    analysisRes.setName(goods.getName());
                    analysisRes.setDate(date.get(j));
                    analysisRes.setValue(strings);
                    analyses.add(analysisRes);
                }
                return Result.success(analyses);

            }else{
                return Result.fail();
            }
        }else{
            return Result.fail();
        }
    }


}
