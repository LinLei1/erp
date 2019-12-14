package com.example.bus.vo;

import com.example.bus.domain.Provider;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProviderVo extends Provider{
    //相当于序列化解密密码
    public static final long serialVersionUID = 1L;

    private Integer page;

    private Integer limit;

    private Integer[] ids;
}
