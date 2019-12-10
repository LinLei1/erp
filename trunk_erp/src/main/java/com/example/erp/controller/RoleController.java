package com.example.erp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.erp.common.*;
import com.example.erp.domain.Permission;
import com.example.erp.domain.Role;
import com.example.erp.domain.User;
import com.example.erp.service.PermissionService;
import com.example.erp.service.RoleService;
import com.example.erp.vo.RoleVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 老林
 * @since 2019-12-03
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;


    @Autowired
    private PermissionService permissionService;

    /**
     * 公告全查询
     * */
    @RequestMapping("loadAllRole")
    public DataGridView loadAllRole(RoleVo roleVo){
        IPage<Role> page = new Page<>(roleVo.getPage(), roleVo.getLimit());
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(roleVo.getName()),"name",roleVo.getName());
        wrapper.like(StringUtils.isNotBlank(roleVo.getRemark()),"remark",roleVo.getRemark());
        wrapper.eq(roleVo.getAvailable()!=null,"available",roleVo.getAvailable());
        wrapper.orderByDesc("createtime");
        this.roleService.page(page,wrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 添加公告
     *
     * */
    @RequestMapping("addRole")
    public ResultObj addRole(RoleVo roleVo){
        try {
            roleVo.setCreatetime(new Date());
            this.roleService.save(roleVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改
     *
     * */
    @RequestMapping("updateRole")
    public ResultObj updateRole(RoleVo roleVo){
        try {
            this.roleService.updateById(roleVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
    /**
     * 根据id删除
     *
     * */
    @RequestMapping("deleteRole")
    public ResultObj deleteRole(Integer id){
        try {
            this.roleService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 根据角色id加载菜单和权限的json串
     * */
    @RequestMapping("initPermissionByRoleId")
    public DataGridView initPermissionByRoleId(Integer roleId){
        //查询所有可用的菜单和权限
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        wrapper.eq("available", Constast.AVAILABLE_TRUE);
        List<Permission> allPermissions = permissionService.list(wrapper);
        //1.根据角色id查询当前角色拥有的权限和菜单
        //2.根据查询出来的菜单ID查询权限和菜单数据
        List<Integer> currentRolePermissions = this.roleService.queryRolePermissionIdsByRid(roleId);
        List<Permission> carrentPermissions = null;
        if(currentRolePermissions.size()>0){//如果有id就去查询
            wrapper.in("id",currentRolePermissions);
           carrentPermissions = permissionService.list(wrapper);
        }else {
            carrentPermissions = new ArrayList<>();
        }

        //构造List<TreeNode>
        List<TreeNode> nodes = new ArrayList<>();
        for (Permission p1: allPermissions) {
           String checkArr="0";
            for (Permission p2: carrentPermissions){
                if (p1.getId()==p2.getId()){
                    checkArr = "1";
                    break;
                }
            }
            Boolean spread = p1.getSpread()==null||p1.getSpread()==1?true:false;
            nodes.add(new TreeNode(p1.getId(),p1.getPid(),p1.getTitle(),spread,checkArr));
        }
        return new DataGridView(nodes);
    }

    /**
     * 保存角色和菜单权限之间的关系
     *
     * */
    @RequestMapping("saveRolePermission")
    public ResultObj saveRolePermission(Integer rid,Integer[] ids){
        try {
            this.roleService.saveRolePermission(rid,ids);
            return ResultObj.DISPATCH_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DISPATCH_ERROR;
        }
    }

}

