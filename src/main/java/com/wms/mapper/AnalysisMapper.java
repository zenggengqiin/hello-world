package com.wms.mapper;

import com.wms.entity.Analysis;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.entity.TopRes;
import net.sf.jsqlparser.statement.select.Top;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2024-02-03
 */
@Mapper
public interface AnalysisMapper extends BaseMapper<Analysis> {
    List<Analysis> getsix(@Param("goodstype") String goodstype);

    List<Analysis> getOut(@Param("goodsid") String goodsid);

    List<Analysis> getfiveID(@Param("goodstype") String goodstype);

    Double [] getmore(@Param("goodsid") Integer goodsid);

    List<String> getdate(@Param("goodsid") Integer goodsid);

    int getAVG(@Param("goodsid") Integer goodsid);

    String getDaySum();
    Analysis getDayMax();
    Analysis getDayMin();
    TopRes getTop();
    ArrayList<Analysis> getSum();
    Double [] getTomorrow(@Param("goodsid") Integer goodsid);
    Double[] getEstimate(@Param("goodsid") String goodsid);
}
