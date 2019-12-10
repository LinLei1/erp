package com.example.erp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.erp.domain.Permission;
import com.example.erp.mapper.PermissionMapper;
import com.example.erp.service.PermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
@Transactional
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Override
    public boolean removeById(Serializable id) {
        PermissionMapper permissionMapper = this.getBaseMapper();
        //根据权限或菜单id删除权限表和角色表里面的数据
        permissionMapper.deleteRolePermissionByPid(id);
        return super.removeById(id);//删除权限表的数据
    }
}
