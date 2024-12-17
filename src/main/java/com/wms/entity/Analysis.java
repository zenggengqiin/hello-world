package com.wms.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
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
 * @since 2024-02-03
 */
@TableName("analysis")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Analysis implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("goodsid")
    private Integer goodsid;

    @TableField("name")
    private String name;

    /**
     * 出库数
     */
    @TableField("value")
    private Integer value;

    @TableField("date")
    private String date;

    /**
     * 0入1出
     */
    @TableField("inandout")
    private Integer inandout;

    @Override
    public String toString() {
        return "Analysis{" +
            "id=" + id +
            ", goodsid=" + goodsid +
            ", name=" + name +
            ", value=" + value +
            ", date=" + date +
            ", inandout=" + inandout +
        "}";
    }

}
