package com.wms.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2024-01-30
 */
@TableName("record")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Record implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 货品id
     */
    @TableField("goods")
    private Integer goods;

    /**
     * 取货人/补货人
     */
    @TableField("userid")
    private Integer userid;

    /**
     * 数量
     */
    @TableField("count")
    private Integer count;

    /**
     * 操作时间
     */
    @TableField("createtime")
    private LocalDateTime createtime;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    @Override
    public String toString() {
        return "Record{" +
            "id=" + id +
            ", goods=" + goods +
            ", userid=" + userid +
            ", count=" + count +
            ", createtime=" + createtime +
            ", remark=" + remark +
        "}";
    }
}
