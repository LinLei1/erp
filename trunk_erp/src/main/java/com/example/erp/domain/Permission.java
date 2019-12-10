package com.example.erp.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_permission")
public class Permission implements Serializable {

    private Integer id;

    private Integer pid;

    private String type;

    private String percode;

    private String title;

    private String icon;

    private String href;

    private String target;

    private Integer spread;

    private Integer ordernum;

    private Integer available;
}