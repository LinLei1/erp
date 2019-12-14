package com.example.bus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 业务管理的路由器
 *
 * */
@Controller
@RequestMapping("/bus")
public class BusinessController {

    /**
     * 跳转到客户管理
     *
     * */
    @RequestMapping("toCustomerManage")
    public String toCustomerManage(){
        return "business/customer/customerManage";
    }

    /**
     * 跳转到供应商管理
     *
     * */
    @RequestMapping("toProviderManage")
    public String toProviderManage(){
        return "business/provider/providerManage";
    }

    /**
     * 跳转到商品管理
     *
     * */
    @RequestMapping("toGoodsManage")
    public String toGoodsManage(){
        return "business/goods/goodsManage";
    }

    /**
     * 跳转到商品管理
     *
     * */
    @RequestMapping("toInportManage")
    public String toInportManage(){
        return "business/Inport/inportManage";
    }
}
