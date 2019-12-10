package com.example.erp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.erp.domain.User;

public interface UserService extends IService<User> {

    /**
     * 保存用户和角色的关系
     *
     * */
    void saveUserRole(Integer uid, Integer[] ids);
}
