package com.example.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys")
public class SystemController {

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "system/index/login";
    }

    /**
     * 跳转到系统首页
     *
     * */
    @RequestMapping("/index")
    public String Index() {
        return "system/index/index";
    }

    /**
     * 跳转到桌面管理界面
     *
     * */
    @RequestMapping("/toDeskManage")
    public String toDeskManage(){
        return "system/index/main";
    }

    /**
     * 跳转到日志管理
     *
     * */
    @RequestMapping("/toLoginfoManage")
    public String toLoginfoManage(){
        return "system/loginfo/loginfoManage";
    }

    /**
     * 跳转到公告管理
     *
     * */
    @RequestMapping("/toNoticeManage")
    public String toNoticeManage(){
        return "system/notice/noticeManage";
    }

    /**
     * 跳转到部门管理
     *
     * */
    @RequestMapping("toDeptManage")
    public String toDeptManage(){
        return "system/dept/deptManage";
    }


    /**
     * 跳转到部门管理-left
     *
     * */
    @RequestMapping("toDeptLeft")
    public String toDeptLeft(){
        return "system/dept/deptLeft";
    }


    /**
     * 跳转到部门管理-right
     *
     * */
    @RequestMapping("toDeptRight")
    public String toDeptRight(){
        return "system/dept/deptRight";
    }

    /**
     * 跳转到菜单管理
     *
     * */
    @RequestMapping("toMenuManage")
    public String toMenuManage(){
        return "system/menu/menuManage";
    }


    /**
     * 跳转到菜单管理-left
     *
     * */
    @RequestMapping("toMenuLeft")
    public String toMenuLeft(){
        return "system/menu/menuLeft";
    }


    /**
     * 跳转到菜单管理-right
     *
     * */
    @RequestMapping("toMenuRight")
    public String toMenuRight(){
        return "system/menu/menuRight";
    }

    /**
     * 跳转到权限管理
     *
     * */
    @RequestMapping("toPermissionManage")
    public String toPermissionManage(){
        return "system/permission/permissionManage";
    }


    /**
     * 跳转到权限管理-left
     *
     * */
    @RequestMapping("toPermissionLeft")
    public String toPermissionLeft(){
        return "system/permission/permissionLeft";
    }


    /**
     * 跳转到权限管理-right
     *
     * */
    @RequestMapping("toPermissionRight")
    public String toPermissionRight(){
        return "system/permission/permissionRight";
    }


    /**
     * 跳转到角色管理
     *
     * */
    @RequestMapping("toRoleManage")
    public String toRoleManage(){
        return "system/role/roleManage";
    }

    /**
     * 跳转到用户管理
     *
     * */
    @RequestMapping("toUserManage")
    public String toUserManage(){
        return "system/user/userManage";
    }
}


