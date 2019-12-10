package com.example.erp.vo;

import com.example.erp.domain.Loginfo;
import com.example.erp.domain.Permission;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class LoginfoVo extends Loginfo {

    public static final long serialVersionUID = 1L;

    private Integer page;

    private Integer limit;

    private Integer[] ids;//接收多个id

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
