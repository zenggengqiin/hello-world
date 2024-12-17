package com.wms.service;

import com.wms.common.Result;
import com.wms.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2024-01-22
 */
public interface IUserService extends IService<User> {

    //添加用户
    boolean save(User user);
    //删除用户
    boolean del(Integer id);
    //修改用户
    boolean mod(User user);
    //查找用户（模糊搜索 匹配)
    List<User> get(User user);
    //查找全部
    List<User> getAll();

    Result login(String num, String password);

}
