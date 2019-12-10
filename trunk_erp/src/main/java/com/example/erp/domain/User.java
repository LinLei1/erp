package com.example.erp.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user")
public class User implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private String loginname;
    private String address;
    private Integer sex;
    private String remark;
    private String pwd;
    private Date hiredate;
    private Integer mgr;
    private Integer deptid;
    private Integer available;
    private Integer ordernum;
    private String salt;
    private Integer type;

    /**
     * 领导名称
     * */
    @TableField(exist = false)
    private String leadername;

    /**
     * 部门名称
     * */
    @TableField(exist = false)
    private String deptname;
}
