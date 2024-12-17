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
 * @since 2024-01-29
 */
@TableName("goodstype")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goodstype implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 分类名
     */
    @TableField("name")
    private String name;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;


    @Override
    public String toString() {
        return "Goodstype{" +
            "id=" + id +
            ", name=" + name +
            ", remark=" + remark +
        "}";
    }
}
