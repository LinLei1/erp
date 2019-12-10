package com.example.erp.vo;

import com.example.erp.domain.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserVo extends User {

    public static final long serialVersionUID = 1L;

    private Integer page = 1;

    private Integer limit = 10;
}
