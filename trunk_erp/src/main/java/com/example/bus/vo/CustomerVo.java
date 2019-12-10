package com.example.bus.vo;

import com.example.bus.domain.Customer;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomerVo extends Customer {

    //相当于序列化解密密码
    public static final long serialVersionUID = 1L;

    private Integer page;

    private Integer limit;

    private Integer[] ids;
}
