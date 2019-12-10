package com.example.erp.vo;

import com.example.erp.domain.Permission;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PermissionVo extends Permission {

    public static final long serialVersionUID = 1L;

    private Integer page;

    private Integer limit;
}
