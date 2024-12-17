package com.wms.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2024-01-22
 */
@TableName("user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 账号
     */
    @TableField("num")
    private String num;

    /**
     * 名字
     */
    @TableField("name")
    private String name;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 性别
     */
    @TableField("sex")
    private Integer sex;

    /**
     * 年龄
     */
    @TableField("age")
    private Integer age;

    /**
     * 电话
     */
    @TableField("phone")
    private String phone;

    /**
     * 权限 0超级管理员1管理员2普通用户
     */
    @TableField("grade")
    private Integer grade;

    /**
     * 是否有效 1有效
     */
    @TableField("isValid")
    private Integer isValid;

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", num=" + num +
            ", name=" + name +
            ", password=" + password +
            ", age=" + age +
            ", sex=" + sex +
            ", phone=" + phone +
            ", grade=" + grade +
            ", isValid=" + isValid +
        "}";
    }
}
