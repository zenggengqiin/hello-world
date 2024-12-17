package com.wms.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.wms.entity.Record;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import com.baomidou.mybatisplus.core.conditions.Wrapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2024-01-30
 */
@Mapper
public interface RecordMapper extends BaseMapper<Record> {

    IPage pageR(IPage<Record> Page, @Param(Constants.WRAPPER)Wrapper wrapper);

}
