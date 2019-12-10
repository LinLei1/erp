package com.example.erp.service;

import com.example.erp.domain.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 老林
 * @since 2019-12-03
 */
public interface RoleService extends IService<Role> {

    /**
     * 根据角色id查询当前角色拥有的权限和菜单id
     *
     * */
    List<Integer> queryRolePermissionIdsByRid(Integer roleId);

    /**
     * 保存角色和菜单权限之间的关系
     *
     * */
    void saveRolePermission(Integer rid, Integer[] ids);

    /**
     * 查询当前用户拥有的角色id集合
     *
     * */
    List<Integer> queryUserRoleIdsByUid(Integer id);
}
