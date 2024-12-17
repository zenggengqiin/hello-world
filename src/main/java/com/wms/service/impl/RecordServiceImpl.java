package com.wms.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.entity.Record;
import com.wms.mapper.RecordMapper;
import com.wms.service.IRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.conditions.Wrapper;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2024-01-30
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements IRecordService {

    @Resource
    private RecordMapper recordMapper;

    @Override
    public IPage pageR(IPage<Record> page, Wrapper wrapper) {
        return recordMapper.pageR(page,wrapper);
    }
}
