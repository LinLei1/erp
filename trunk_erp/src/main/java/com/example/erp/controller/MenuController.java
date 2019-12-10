package com.example.erp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.erp.common.*;
import com.example.erp.domain.Dept;
import com.example.erp.domain.Permission;
import com.example.erp.domain.User;
import com.example.erp.service.PermissionService;
import com.example.erp.service.RoleService;
import com.example.erp.vo.DeptVo;
import com.example.erp.vo.PermissionVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("menu")
public class MenuController {
    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("loadIndexLeftMenuJson")
    public DataGridView loadIndexLeftMenuJson(PermissionVo permissionVo){
        //查询所有菜单
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        //设置只查询菜单
        wrapper.eq("type", Constast.TYPE_MENU);
        wrapper.eq("available",Constast.AVAILABLE_TRUE);

        User user = (User) WebUtils.getSession().getAttribute("user");
        List<Permission> list = null;
        if (user.getType()==Constast.USER_TYPE_SUPER){
            list = permissionService.list(wrapper);
        }else {
            //根据用户Id，角色，权限去查询
            Integer userId = user.getId();
            //根据用户id查询角色
            List<Integer> currentUserRoleIds = roleService.queryUserRoleIdsByUid(userId);
            //根据角色id取到权限和菜单id
            Set<Integer> pids = new HashSet<>();
            for (Integer rid: currentUserRoleIds){
                List<Integer> permissionIds = roleService.queryRolePermissionIdsByRid(rid);
                pids.addAll(permissionIds);
            }
            //根据id查询权限
            if (pids.size()>0){
                wrapper.in("id",pids);
                list = permissionService.list(wrapper);
            }else {
                list = new ArrayList<>();
            }
        }
        List<TreeNode> treeNodes = new ArrayList<>();
        for (Permission permission: list) {
            Integer id = permission.getId();
            Integer pid = permission.getPid();
            String title = permission.getTitle();
            String icon = permission.getIcon();
            String href = permission.getHref();
            Boolean spread = permission.getSpread()==Constast.SPREAD_TRUE?true:false;
            treeNodes.add(new TreeNode(id,pid,title,icon,href,spread));
        }
        List<TreeNode> nodes = TreeNodeBuilder.build(treeNodes, Constast.MENU_PARENT_ID);
        return new DataGridView(nodes);
    }

    /*------------------------菜单管理开始------------------------------------*/

    /**
     * 加载菜单管理左边的菜单树的json
     *
     * */
    @RequestMapping("loadMenuManageLeftTreeJson")
    public DataGridView loadMenuManageLeftTreeJson(){
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
     * 菜单管理全查询
     *
     * */
    @RequestMapping("loadAllMenu")
    public DataGridView loadAllMenu(PermissionVo permissionVo){
        IPage<Permission> page = new Page<>(permissionVo.getPage(), permissionVo.getLimit());
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        wrapper.eq(permissionVo.getId()!=null,"id",permissionVo.getId()).or()
                .eq(permissionVo.getId()!=null,"pid",permissionVo.getId());
        wrapper.eq("type",Constast.TYPE_MENU);
        wrapper.like(StringUtils.isNotBlank(permissionVo.getTitle()),"title",permissionVo.getTitle());
        wrapper.orderByAsc("ordernum");
        this.permissionService.page(page,wrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 添加菜单
     *
     * */
    @RequestMapping("addMenu")
    public ResultObj addMenu(PermissionVo permissionVo){
        try {
            permissionVo.setType(Constast.TYPE_MENU);//设置类型
            this.permissionService.save(permissionVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改菜单
     *
     * */
    @RequestMapping("updateMenu")
    public ResultObj updateMenu(PermissionVo permissionVo){
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
    @RequestMapping("loadMaxMenuOrderNum")
    public Map<String,Object> loadMaxMenuOrderNum(){
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
     * 查询当前菜单是否有子菜单
     *
     * */
    @RequestMapping("checkMenuHasChildrenNode")
    public Map<String,Object> checkMenuHasChildrenNode(PermissionVo permissionVo){
        Map<String,Object> map = new HashMap<>();
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        wrapper.eq("pid",permissionVo.getId());
        List<Permission> list = this.permissionService.list(wrapper);
        if (list.size()>0){
            map.put("value",true);
        }else {
            map.put("value",false);
        }
        return map;
    }

    /**
     * 删除菜单
     *
     * */
    @RequestMapping("deleteMenu")
    public ResultObj deleteMenu(PermissionVo permissionVo){
        try {
            this.permissionService.removeById(permissionVo.getId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    /*------------------------菜单管理结束-----------------------------------*/
}
