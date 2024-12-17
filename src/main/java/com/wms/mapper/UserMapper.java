package com.wms.mapper;

import com.wms.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2024-01-22
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
   List<User> selectAll();
}
