package com.example.erp.controller;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.erp.common.Constast;
import com.example.erp.common.DataGridView;
import com.example.erp.common.PinYinUtil;
import com.example.erp.common.ResultObj;
import com.example.erp.domain.Dept;
import com.example.erp.domain.Role;
import com.example.erp.domain.User;
import com.example.erp.service.DeptService;
import com.example.erp.service.RoleService;
import com.example.erp.service.UserService;
import com.example.erp.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    DeptService deptService;

    @Autowired
    RoleService roleService;


    /**
     * 用户全查询
     *
     * */
    @RequestMapping("loadAllUser")
    public DataGridView loadAllUser(UserVo userVo){
        IPage<User> page = new Page<>(userVo.getPage(),userVo.getLimit());
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(userVo.getName()),"loginname",userVo.getName()).or().eq(StringUtils.isNotBlank(userVo.getName()),"name",userVo.getName());
        wrapper.eq(StringUtils.isNotBlank(userVo.getAddress()),"address",userVo.getAddress());
        wrapper.eq("type", Constast.USER_TYPE_NORMAL);
        wrapper.eq("available",1);
        wrapper.eq(userVo.getDeptid()!=null,"deptid",userVo.getDeptid());
        this.userService.page(page,wrapper);

        List<User> list = page.getRecords();
        for (User user: list) {
            Integer deptid = user.getDeptid();
            if (deptid!=null){
                Dept one = deptService.getById(deptid);
                user.setDeptname(one.getTitle());
            }
            Integer mgr = user.getMgr();
            if (mgr!=null){
                User one = userService.getById(mgr);
                user.setLeadername(one.getName());
            }
        }
        return new DataGridView(page.getTotal(),list);
    }


    /**
     * 加载最大排序码
     *
     * */
    @RequestMapping("loadMaxUserOrderNum")
    public Map<String,Object> loadMaxUserOrderNum(){
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("ordernum");
        IPage page = new Page<>(1,1);
        List<User> list = this.userService.page(page,wrapper).getRecords();
        if (list.size()>0){
            map.put("value",list.get(0).getOrdernum()+1);
        }else {
            map.put("value",1);
        }
        return map;
    }

    /**
     * 根据部门id查询用户
     * */
    @RequestMapping("loadUserByDeptId")
    public DataGridView loadUserByDeptId(Integer deptid){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq(deptid!=null,"deptid",deptid);
        wrapper.eq("available",Constast.AVAILABLE_TRUE);
        wrapper.eq("type",Constast.USER_TYPE_NORMAL);
        List<User> list = this.userService.list(wrapper);
        return new DataGridView(list);
    }

    /**
     * 把用户名转成拼音
     * */
    @RequestMapping("changeChineseToPinyin")
    public Map<String,Object> changeChineseToPinyin(String username){
        Map<String,Object> map = new HashMap<>();
        if (username!=null){
            map.put("value", PinYinUtil.getPingYin(username));
        }else {
            map.put("value","");
        }
        return map;
    }

    /**
     * 添加用户
     * */
    @RequestMapping("addUser")
    public ResultObj addUser(UserVo userVo){
        try {
            userVo.setType(Constast.USER_TYPE_NORMAL);//设置类型
            userVo.setHiredate(new Date());
            String salt = IdUtil.simpleUUID().toUpperCase();
            userVo.setSalt(salt);
            userVo.setPwd(new Md5Hash(Constast.USER_DEFAULT_PWD,salt,2).toString());//设置密码
            this.userService.save(userVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 根据用户id查询一个用户
     *
     * */
    @RequestMapping("loadUserById")
    public DataGridView loadUserById(Integer id){
        return new DataGridView(this.userService.getById(id));
    }

    /**
     * 修改用户
     * */
    @RequestMapping("updateUser")
    public ResultObj updateUser(UserVo userVo){
        try {
            this.userService.updateById(userVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }


    /**
     * 删除用户
     *
     * */
    @RequestMapping("deleteUser")
    public ResultObj deleteUser(Integer id){
        try {
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("mgr",id);
            List<User> list = userService.list(wrapper);
            if (list.size()>0){
                for (User user: list){
                    user.setMgr(2);
                    this.userService.updateById(user);
                }
            }
            User user = new User();
            user.setId(id);
            user.setAvailable(0);
            this.userService.updateById(user);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }


    /**
     * 重置密码
     *
     * */
    @RequestMapping("resetPwd")
    public ResultObj resetPwd(Integer id){
        try {
            User user = new User();
            user.setId(id);
            String salt = IdUtil.simpleUUID().toUpperCase();
            user.setSalt(salt);
            user.setPwd(new Md5Hash(Constast.USER_DEFAULT_PWD,salt,2).toString());
            this.userService.updateById(user);
            return ResultObj.RESET_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.RESET_ERROR;
        }
    }

    @RequestMapping("initRoleByUserId")
    public DataGridView initRoleByUserId(Integer id){
        //查询所有可用的角色
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.eq("available",Constast.AVAILABLE_TRUE);
        List<Map<String, Object>> listMaps = this.roleService.listMaps(wrapper);
        //查询当前用户拥有的角色id集合
        List<Integer> currentUserRoleIds = this.roleService.queryUserRoleIdsByUid(id);
        for (Map<String,Object>map: listMaps){
            Boolean LAY_CHECKED = false;
            Integer roleId = (Integer)map.get("id");
            for (Integer rid: currentUserRoleIds){
                if (rid==roleId){
                    LAY_CHECKED = true;
                    break;
                }
            }
            map.put("LAY_CHECKED",LAY_CHECKED);
        }
        return new DataGridView(Long.valueOf(listMaps.size()),listMaps);
    }

    /**
     * 保存用户和角色的关系
     *
     * */
    @RequestMapping("saveUserRole")
    public ResultObj saveUserRole(Integer uid,Integer[] ids){
        try {
            this.userService.saveUserRole(uid,ids);
            return ResultObj.DISPATCH_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DISPATCH_ERROR;
        }
    }
}
