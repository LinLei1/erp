package com.example.erp.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2019-11-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_dept")
public class Dept implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 部门编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 父级部门编号
     */
    private Integer pid;

    /**
     * 名称
     */
    private String title;

    /**
     * 0不展开，1展开
     */
    private Integer spread;

    /**
     * 所在地
     * */
    private String address;

    /**
     * 备注
     */
    private String remark;

    /**
     * 0不可用，1可用
     */
    private Integer available;

    /**
     * 加入时间
     * */
    private Date createtime;

    /**
     * 排序码
     */
    private Integer ordernum;


}
