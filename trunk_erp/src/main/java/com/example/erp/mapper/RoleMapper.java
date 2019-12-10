package com.example.erp.mapper;

import com.example.erp.domain.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 老林
 * @since 2019-12-03
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 根据角色id删除sys_role_permission
     *
     * */
    void deleteRolePermissionByRid(Serializable id);

    /**
     * 根据角色id删除sys_role_permission
     *
     */
    void deleteRoleUserByRid(Serializable id);


    /**
     * 根据角色id查询当前角色拥有的权限和菜单id
     *
     * */
    List<Integer> queryRolePermissionIdsByRid(Integer roleId);

    /**
     * 保存角色和菜单权限之间的关系
     *
     * */
    void saveRolePermission(@Param("rid") Integer rid, @Param("pid") Integer pid);


    /**
     * 根据用户id删除用户角色中间表的数据
     *
     * */
    void deleteRoleUserByUid(@Param("id") Serializable id);

    /**
     *查询当前用户拥有的角色id集合
     *
     * */
    List<Integer> queryUserRoleIdsByUid(Integer id);

    /**
     * 保存用户和角色的关系
     *
     * */
    void insertUserRole(@Param("uid")Integer uid,@Param("rid")Integer rid);
}
