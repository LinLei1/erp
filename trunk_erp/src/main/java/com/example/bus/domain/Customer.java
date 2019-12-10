package com.example.bus.domain;

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
 * @since 2019-12-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_customer")
public class Customer implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 客户编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 客户全称
     */
    private String customername;

    /**
     * 客户邮编
     */
    private String zip;

    /**
     * 客户公司地址
     */
    private String address;

    /**
     * 客户公司电话
     */
    private String telephone;

    /**
     * 联系人
     */
    private String connectionperson;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 卡户银行
     */
    private String bank;

    /**
     * 银行账户
     */
    private String account;

    /**
     * 联系人信箱
     */
    private String email;

    /**
     * 客户传真
     */
    private String fax;

    /**
     * 0不可用1可用
     */
    private Integer available;


}
