package com.example.erp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.erp.common.Constast;
import com.example.erp.common.DataGridView;
import com.example.erp.common.ResultObj;
import com.example.erp.common.TreeNode;
import com.example.erp.domain.Dept;
import com.example.erp.service.DeptService;
import com.example.erp.vo.DeptVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 老林
 * @since 2019-11-14
 */
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    DeptService deptService;

    /**
     * 加载部门管理左边的部门树的json
     *
     * */
    @RequestMapping("loadDeptManageLeftTreeJson")
    public DataGridView loadDeptManageLeftTreeJson(){
        List<Dept> list = this.deptService.list();
        List<TreeNode> treeNodes = new ArrayList<>();
        for (Dept dept: list) {
            Boolean spread = dept.getSpread()==Constast.SPREAD_TRUE?true:false;
            TreeNode node = new TreeNode(dept.getId(), dept.getPid(), dept.getTitle(), spread);
            treeNodes.add(node);
        }
        return new DataGridView(treeNodes);
    }
    
    /**
     * 部门管理全查询
     * 
     * */
    @RequestMapping("loadAllDept")
    public DataGridView loadAllDept(DeptVo deptVo){
        IPage<Dept> page = new Page<>(deptVo.getPage(), deptVo.getLimit());
        QueryWrapper<Dept> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(deptVo.getTitle()),"title",deptVo.getTitle());
        wrapper.like(StringUtils.isNotBlank(deptVo.getAddress()),"address",deptVo.getAddress());
        wrapper.like(StringUtils.isNotBlank(deptVo.getRemark()),"remark",deptVo.getRemark());
        wrapper.eq(deptVo.getId()!=null,"id",deptVo.getId()).or()
                .eq(deptVo.getId()!=null,"pid",deptVo.getId());
        wrapper.orderByAsc("ordernum");
        this.deptService.page(page,wrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 添加部门
     *
     * */
    @RequestMapping("addDept")
    public ResultObj addDept(DeptVo deptVo){
        try {
            deptVo.setCreatetime(new Date());
            this.deptService.save(deptVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改部门
     *
     * */
    @RequestMapping("updateDept")
    public ResultObj updateDept(DeptVo deptVo){
        try {
            deptVo.setCreatetime(new Date());
            this.deptService.updateById(deptVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 加载最大排序码
     *
     * */
    @RequestMapping("loadMaxDeptOrderNum")
    public Map<String,Object> loadMaxDeptOrderNum(){
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<Dept> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("ordernum");
        List<Dept> list = this.deptService.list(wrapper);
        if (list.size()>0){
            map.put("value",list.get(0).getOrdernum());
        }else {
            map.put("value",1);
        }
        return map;
    }

    /**
     * 查询当前部门是否有子部门
     *
     * */
    @RequestMapping("checkDeptHasChildrenNode")
    public Map<String,Object> checkDeptHasChildrenNode(DeptVo deptVo){
        Map<String,Object> map = new HashMap<>();
        QueryWrapper<Dept> wrapper = new QueryWrapper<>();
        wrapper.eq("pid",deptVo.getId());
        List<Dept> list = this.deptService.list(wrapper);
        if (list.size()>0){
            map.put("value",true);
        }else {
            map.put("value",false);
        }
        return map;
    }

    /**
     * 删除部门
     *
     * */
    @RequestMapping("deleteDept")
    public ResultObj deleteDept(DeptVo deptVo){
        try {
            this.deptService.removeById(deptVo.getId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}

