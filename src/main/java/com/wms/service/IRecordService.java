package com.wms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.entity.Record;
import com.baomidou.mybatisplus.extension.service.IService;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2024-01-30
 */
public interface IRecordService extends IService<Record> {

    IPage pageR(IPage<Record> page, Wrapper wrapper);

}
