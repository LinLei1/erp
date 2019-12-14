package com.example.bus.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 老林
 * @since 2019-12-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_goods")
public class Goods implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 商品编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品名称
     */
    private String goodsname;

    /**
     * 产地
     */
    private String produceplace;

    /**
     * 规格
     */
    private String size;

    /**
     * 包装
     */
    private String goodspackage;

    /**
     * 生产批号
     */
    private String productcode;

    /**
     * 批准文号
     */
    private String promitcode;

    /**
     * 描述
     */
    private String description;

    /**
     * 销售价格
     */
    private Double price;

    /**
     * 供应商编号
     */
    private Integer providerid;

    /**
     * 状态
     */
    private Integer available;

    /**
     * 库存数量
     */
    private Integer number;

    /**
     * 预警库存
     */
    private Integer dangernum;

    /**
     * 商品图片
     */
    private String goodsimg;

    /**
     *供应商名称
     * */
    @TableField(exist = false)
    private String providername;

}
