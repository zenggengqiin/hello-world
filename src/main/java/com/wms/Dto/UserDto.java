package com.wms.Dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class UserDto {
    private Integer id;

    /**
     * 账号
     */
    private String num;

    /**
     * 名字
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 电话
     */
    private String phone;

    /**
     * 权限 0超级管理员1管理员2普通用户
     */
    private Integer grade;

    /**
     * 是否有效 1有效
     */
    private Integer isValid;

    // 存储token
    private String token;
}
