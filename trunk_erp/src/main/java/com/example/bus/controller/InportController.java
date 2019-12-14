package com.example.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.bus.domain.Goods;
import com.example.bus.domain.Inport;
import com.example.bus.domain.Provider;
import com.example.bus.service.GoodsService;
import com.example.bus.service.InportService;
import com.example.bus.service.ProviderService;
import com.example.bus.vo.GoodsVo;
import com.example.bus.vo.InportVo;
import com.example.erp.common.*;
import com.example.erp.domain.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 老林
 * @since 2019-12-14
 */
@RestController
@RequestMapping("inport")
public class InportController {

    @Autowired
    private InportService inportService;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private GoodsService goodsService;

    /**
     * 进货全查询
     * */
    @RequestMapping("loadAllInport")
    public DataGridView loadAllInport(InportVo inportVo){
        IPage<Inport> page = new Page<>(inportVo.getPage(), inportVo.getLimit());
        QueryWrapper<Inport> wrapper = new QueryWrapper<>();
        wrapper.eq(inportVo.getProviderid()!=null&&inportVo.getProviderid()!=0,"providerid",inportVo.getProviderid());
        wrapper.eq(inportVo.getGoodsid()!=null&&inportVo.getGoodsid()!=0,"goodsid",inportVo.getGoodsid());
        wrapper.like(inportVo.getOperateperson()!=null,"operateperson",inportVo.getOperateperson());
        wrapper.like(inportVo.getRemark()!=null,"remark",inportVo.getRemark());
        wrapper.ge(inportVo.getStartTime()!=null,"inporttime",inportVo.getStartTime());
        wrapper.le(inportVo.getEndTime()!=null,"inporttime",inportVo.getEndTime());
        wrapper.orderByDesc("inporttime");
        this.inportService.page(page,wrapper);
        List<Inport> list = page.getRecords();
        for (Inport inport: list){
            Provider provider = this.providerService.getById(inport.getProviderid());
            if (null!=provider) {
                inport.setProvidername(provider.getProvidername());
            }
            Goods goods = this.goodsService.getById(inport.getGoodsid());
            if (null!=goods){
                inport.setGoodsname(goods.getGoodsname());
                inport.setSize(goods.getSize());
            }
        }
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 添加进货
     *
     * */
    @RequestMapping("addInport")
    public ResultObj addInport(InportVo inportVo){
        try {
            inportVo.setInporttime(new Date());
            User user = (User) WebUtils.getSession().getAttribute("user");
            inportVo.setOperateperson(user.getName());
            this.inportService.save(inportVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }


    /**
     * 根据id删除进货
     *
     * */
    @RequestMapping("deleteInport")
    public ResultObj deleteInport(Integer id){
        try {
            this.inportService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 修改
     *
     * */
    @RequestMapping("updateInport")
    public ResultObj updateInport(InportVo inportVo){
        try {
            inportService.updateById(inportVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 加载进货的下拉树
     * */
    @RequestMapping("loadAllInportForSelect")
    public DataGridView loadAllInportForSelect(){
        QueryWrapper<Inport> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("available", Constast.AVAILABLE_TRUE);
        List<Inport> list = this.inportService.list(queryWrapper);
        for (Inport inport: list){
            Provider provider = this.providerService.getById(inport.getProviderid());
            if (null!=provider) {
                inport.setProvidername(provider.getProvidername());
            }
        }
        return new DataGridView(list);
    }
}

