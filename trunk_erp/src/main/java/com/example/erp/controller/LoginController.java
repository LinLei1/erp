package com.example.erp.controller;

import com.example.erp.common.ActiveUser;
import com.example.erp.common.ResultObj;
import com.example.erp.common.WebUtils;
import com.example.erp.domain.Loginfo;
import com.example.erp.service.LoginfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    LoginfoService loginfoService;

    @RequestMapping("login")
    public ResultObj login(String loginname, String pwd) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginname, pwd);
        try {
            subject.login(token);
            ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
            WebUtils.getSession().setAttribute("user", activeUser.getUser());
            //记录登录日志
            Loginfo entity = new Loginfo();
            entity.setLoginname(activeUser.getUser().getName()+"-"+activeUser.getUser().getLoginname());
            //获取ip
            entity.setLoginip(WebUtils.getRequest().getRemoteAddr());
            entity.setLogintime(new Date());
            loginfoService.save(entity);
            return ResultObj.LOGIN_SUCCESS;
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return ResultObj.LOGIN_ERROR_PASS;
        }
    }

}

