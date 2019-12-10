package com.example.erp.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultObj {


    /**
     * 登录操作返回数据
     * */
    public static final ResultObj LOGIN_SUCCESS = new ResultObj(Constast.OK, "登录成功");
    public static final ResultObj LOGIN_ERROR_PASS = new ResultObj(Constast.ERROR, "登录失败,用户名或密码不正确");
    public static final ResultObj LOGIN_ERROR_CODE = new ResultObj(Constast.ERROR, "登录失败,验证码不正确");


    /**
     * 删除操作返回数据
     * */
    public static final ResultObj DELETE_SUCCESS = new ResultObj(Constast.OK,"删除成功");
    public static final ResultObj DELETE_ERROR = new ResultObj(Constast.ERROR,"删除失败");

    /**
     * 更新操作返回数据
     * */
    public static final ResultObj UPDATE_SUCCESS = new ResultObj(Constast.OK,"更新成功");
    public static final ResultObj UPDATE_ERROR = new ResultObj(Constast.ERROR,"更新失败");

    /**
     * 添加操作返回数据
     * */
    public static final ResultObj ADD_SUCCESS = new ResultObj(Constast.OK,"添加成功");
    public static final ResultObj ADD_ERROR = new ResultObj(Constast.ERROR,"添加失败");

    /**
     * 重置操作返回数据
     * */
    public static final ResultObj RESET_SUCCESS = new ResultObj(Constast.OK,"重置成功");
    public static final ResultObj RESET_ERROR = new ResultObj(Constast.ERROR,"重置失败");

    /**
     * 分发操作返回数据
     * */
    public static final ResultObj DISPATCH_SUCCESS = new ResultObj(Constast.OK,"分发成功");
    public static final ResultObj DISPATCH_ERROR = new ResultObj(Constast.ERROR,"分发失败");


    private Integer code;
    private String msg;
}
