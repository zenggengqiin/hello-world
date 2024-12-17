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
@TableName("goods")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 货物名称
     */
    @TableField("name")
    private String name;

    /**
     * 仓库名
     */
    @TableField("storage")
    private Integer storage;

    /**
     * 物品分类
     */
    @TableField("goodstype")
    private Integer goodstype;

    /**
     * 物品数量
     */
    @TableField("count")
    private Integer count;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;


    @Override
    public String toString() {
        return "Goods{" +
            "id=" + id +
            ", name=" + name +
            ", storage=" + storage +
            ", goodstype=" + goodstype +
            ", count=" + count +
            ", remark=" + remark +
        "}";
    }
}
