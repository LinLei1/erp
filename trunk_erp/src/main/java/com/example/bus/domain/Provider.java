package com.example.bus.domain;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("bus_provider")
public class Provider implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 供应商编号
     */
    private Integer id;

    /**
     * 供应商全称
     */
    private String providername;

    /**
     * 供应商邮编
     */
    private String zip;

    /**
     * 公司地址
     */
    private String address;

    /**
     * 公司电话
     */
    private String telephone;

    /**
     * 联系人
     */
    private String connectionperson;

    /**
     * 联系人电话
     */
    private String phone;

    /**
     * 开户银行
     */
    private String bank;

    /**
     * 银行账户
     */
    private String account;

    /**
     * 联系人邮箱
     */
    private String email;

    /**
     * 公司传真
     */
    private String fax;

    /**
     * 0不可用，1可用
     */
    private Integer available;


}
