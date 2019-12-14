package com.example.bus.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
 * @since 2019-12-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_outport")
public class Outport implements Serializable {

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
     * 支付类型
     */
    private String paytype;

    /**
     * 进货时间
     */
    private LocalDateTime outputtime;

    /**
     * 操作员管理
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
     * 商品编号
     */
    private String goodsid;

    /**
     * 价格
     */
    private Double outportprice;


}
