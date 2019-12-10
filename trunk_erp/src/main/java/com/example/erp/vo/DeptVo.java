package com.example.erp.vo;

import com.example.erp.domain.Dept;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DeptVo extends Dept {

    //相当于序列化解密密码
    public static final long serialVersionUID = 1L;

    private Integer page;

    private Integer limit;

}
