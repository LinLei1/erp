package com.example.erp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.erp.domain.User;
import com.example.erp.mapper.RoleMapper;
import com.example.erp.mapper.UserMapper;
import com.example.erp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    RoleMapper roleMapper;


    @Override
    public boolean updateById(User entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        //根据用户id删除用户角色中间表的数据
         roleMapper.deleteRoleUserByUid(id);
        //删除用户头像[如果是默认头像不删除,否则删除]

        return super.removeById(id);
    }

    @Override
    public boolean save(User entity) {
        return super.save(entity);
    }

    @Override
    public User getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public void saveUserRole(Integer uid, Integer[] ids) {
        //根据用户id删除sys_role_user里面的数据
        this.roleMapper.deleteRoleUserByUid(uid);
        if (null!=ids&&ids.length>0){
            for (Integer rid: ids){
                this.roleMapper.insertUserRole(uid,rid);
            }
        }
    }
}
