package com.wms.service.impl;

import com.wms.common.Result;
import com.wms.entity.Analysis;
import com.wms.entity.TopRes;
import com.wms.mapper.AnalysisMapper;
import com.wms.service.IAnalysisService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2024-02-03
 */
@Service
public class AnalysisServiceImpl extends ServiceImpl<AnalysisMapper, Analysis> implements IAnalysisService {

    @Autowired
    private AnalysisMapper analysisMapper;

    @Override
    public String getDaySum() {
        return analysisMapper.getDaySum();
    }

    @Override
    public String getDayMax() {
        Analysis analysis = analysisMapper.getDayMax();
        if(analysis!=null){
            String str;
            str = analysis.getName()+": "+analysis.getValue().toString();
            return str;
        }
        return "null";
    }

    @Override
    public String getDayMin() {
        Analysis analysis = analysisMapper.getDayMin();
        if(analysis!=null){
            String str = new String();
            str = analysis.getName()+": "+analysis.getValue();
            return str;
        }
        return "null";
    }

    @Override
    public TopRes getTop() {
        TopRes topRes = analysisMapper.getTop();
        if(topRes!=null){
            return topRes;
        }else{
            return null;
        }
    }
}
