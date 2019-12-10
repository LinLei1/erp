package com.example.erp.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 老林
 * @since 2019-11-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_loginfo")
public class Loginfo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 编号
     */
    private Integer id;

    /**
     * 登录用户信息
     */
    private String loginname;


    /**
     * 登录ip
     */
    private String loginip;

    /**
     * 登录时间
     */
    private Date logintime;


}
