package com.example.erp.vo;

import com.example.erp.domain.Notice;
import com.example.erp.domain.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class RoleVo extends Role {

    public static final long serialVersionUID = 1L;

    private Integer page = 1;

    private Integer limit = 10;

}
