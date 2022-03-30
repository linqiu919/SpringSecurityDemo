package com.example.securitydemo.service;

import com.example.securitydemo.entity.BsUser;

import java.util.Set;

/**
 * @author chenxinbao
 * @version 1.0
 * @date 2022/3/28 21:55
 */
public interface UserPermissionService {


    /**
     * 获取登录用户的权限信息
     * @author chenxb
     * @return
     * @date 2022/3/28 21:56
     */
    Set<String> getUserPermission(BsUser user);

}
