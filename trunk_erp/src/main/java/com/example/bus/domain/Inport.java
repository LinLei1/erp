package com.example.bus.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 老林
 * @since 2019-12-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_inport")
public class Inport implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 供应商编号
     */
    private Integer providerid;

    /**
     * 支付类型1支付宝2微信3现金
     */
    private String paytype;

    /**
     * 进货时间
     */
    private Date inporttime;

    /**
     * 操作员管理表的name
     */
    private String operateperson;

    /**
     * 数量
     */
    private Integer number;

    /**
     * 注释
     */
    private String remark;

    /**
     * 进货价格
     */
    private Double inportprice;

    /**
     * 商品编号
     */
    private Integer goodsid;

    /**
     *供应商名称
     * */
    @TableField(exist = false)
    private String providername;

    /**
     * 商品名称
     * */
    @TableField(exist = false)
    private String goodsname;

    /**
     * 商品规格
     * */
    @TableField(exist = false)
    private String size;


}
