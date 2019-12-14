package com.example.bus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.bus.domain.Provider;
import com.example.bus.service.ProviderService;
import com.example.bus.vo.ProviderVo;
import com.example.erp.common.Constast;
import com.example.erp.common.DataGridView;
import com.example.erp.common.ResultObj;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 老林
 * @since 2019-12-11
 */
@RestController
@RequestMapping("/provider")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    /**
     * 客户全查询
     */
    @RequestMapping("loadAllProvider")
    public DataGridView loadAllProvider(ProviderVo providerVo) {
        IPage<Provider> page = new Page<>(providerVo.getPage(), providerVo.getLimit());
        QueryWrapper<Provider> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(providerVo.getProvidername()), "providername", providerVo.getProvidername());
        wrapper.like(StringUtils.isNotBlank(providerVo.getPhone()), "phone", providerVo.getPhone());
        wrapper.like(StringUtils.isNotBlank(providerVo.getConnectionperson()), "connectionperson", providerVo.getConnectionperson());
        this.providerService.page(page, wrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 添加客户
     */
    @RequestMapping("addProvider")
    public ResultObj addProvider(ProviderVo providerVo) {
        try {
            this.providerService.saveOrUpdate(providerVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 根据id删除客户
     */
    @RequestMapping("deleteProvider")
    public ResultObj deleteProvider(Integer id) {
        try {
            this.providerService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 根据ids删除客户
     */
    @RequestMapping("batchDeleteProvider")
    public ResultObj batchDeleteProvider(ProviderVo providerVo) {
        Collection<Serializable> list = new ArrayList<>();
        for (Integer id : providerVo.getIds()) {
            list.add(id);
        }
        try {
            this.providerService.removeByIds(list);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    @RequestMapping("updateProvider")
    public ResultObj updateProvider(ProviderVo providerVo) {
        try {
            providerService.updateById(providerVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    @RequestMapping("loadAllProviderForSelect")
    public DataGridView loadAllProviderForSelect(){
        QueryWrapper<Provider> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("available", Constast.AVAILABLE_TRUE);
        List<Provider> list = this.providerService.list(queryWrapper);
        return new DataGridView(list);
    }

}