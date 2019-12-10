package com.example.erp.common;

public class Constast {

    /*
    * 状态码
    * */
    public static final Integer OK = 200;
    public static final Integer ERROR = -1;

    /*
    * 菜单权限类型
    * */
    public static final String TYPE_MENU = "menu";
    public static final String TYPE_PERMISSION = "permission";

    /*
    * 可选状态
    * */
    public static final Object AVAILABLE_TRUE = 1;
    public static final Object AVAILABLE_FALSE = 0;

    /*
    * 用户类型
    * */
    public static final Integer USER_TYPE_SUPER = 0;
    public static final Integer USER_TYPE_NORMAL = 1;

    /*
    * 导航树是否展开
    * */
    public static final Integer SPREAD_TRUE = 1;
    public static final Integer SPREAD_FALSE = 0;

    /*
    * 导航树根节点pid
    * */
    public static final Integer MENU_PARENT_ID =1 ;
    public static final String USER_DEFAULT_PWD ="123456" ;
}
