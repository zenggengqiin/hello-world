package com.wms.service;

import com.wms.common.Result;
import com.wms.entity.Analysis;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.entity.TopRes;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2024-02-03
 */
public interface IAnalysisService extends IService<Analysis> {
    String getDaySum();
    String getDayMax();
    String getDayMin();
    TopRes getTop();
}
