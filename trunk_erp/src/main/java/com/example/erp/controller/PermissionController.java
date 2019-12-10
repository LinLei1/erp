package com.example.erp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.erp.common.Constast;
import com.example.erp.common.DataGridView;
import com.example.erp.common.ResultObj;
import com.example.erp.common.TreeNode;
import com.example.erp.domain.Permission;
import com.example.erp.service.PermissionService;
import com.example.erp.vo.PermissionVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("permission")
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    /*------------------------权限管理开始------------------------------------*/

    /**
     * 加载权限管理左边的权限树的json
     *
     * */
    @RequestMapping("loadPermissionManageLeftTreeJson")
    public DataGridView loadPermissionManageLeftTreeJson(){
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        wrapper.eq("type",Constast.TYPE_MENU);
        List<Permission> list = this.permissionService.list(wrapper);
        List<TreeNode> treeNodes = new ArrayList<>();
        for (Permission permission: list) {
            Boolean spread = permission.getSpread()==Constast.SPREAD_TRUE?true:false;
            TreeNode node = new TreeNode(permission.getId(), permission.getPid(), permission.getTitle(), spread);
            treeNodes.add(node);
        }
        return new DataGridView(treeNodes);
    }

    /**
     * 权限管理全查询
     *
     * */
    @RequestMapping("loadAllPermission")
    public DataGridView loadAllPermission(PermissionVo permissionVo){
        IPage<Permission> page = new Page<>(permissionVo.getPage(), permissionVo.getLimit());
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        wrapper.eq("type",Constast.TYPE_PERMISSION);
        wrapper.like(StringUtils.isNotBlank(permissionVo.getTitle()),"title",permissionVo.getTitle());
        wrapper.like(StringUtils.isNotBlank(permissionVo.getPercode()),"percode",permissionVo.getPercode());
        wrapper.eq(permissionVo.getId()!=null,"pid",permissionVo.getId());
        wrapper.orderByAsc("ordernum");
        this.permissionService.page(page,wrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 添加权限
     *
     * */
    @RequestMapping("addPermission")
    public ResultObj addPermission(PermissionVo permissionVo){
        try {
            permissionVo.setType(Constast.TYPE_PERMISSION);//设置类型
            this.permissionService.save(permissionVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改权限
     *
     * */
    @RequestMapping("updatePermission")
    public ResultObj updatePermission(PermissionVo permissionVo){
        try {
            this.permissionService.updateById(permissionVo);
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
    @RequestMapping("loadMaxPermissionOrderNum")
    public Map<String,Object> loadMaxPermissionOrderNum(){
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("ordernum");
        List<Permission> list = this.permissionService.list(wrapper);
        if (list.size()>0){
            map.put("value",list.get(0).getOrdernum());
        }else {
            map.put("value",1);
        }
        return map;
    }


    /**
     * 删除权限
     *
     * */
    @RequestMapping("deletePermission")
    public ResultObj deletePermission(PermissionVo permissionVo){
        try {
            this.permissionService.removeById(permissionVo.getId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    /*------------------------权限管理结束-----------------------------------*/
}
