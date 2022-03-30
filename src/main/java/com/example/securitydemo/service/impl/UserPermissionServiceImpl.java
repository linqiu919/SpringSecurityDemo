package com.example.securitydemo.service.impl;

import com.example.securitydemo.entity.BsUser;
import com.example.securitydemo.mapper.BsUserComplexMapper;
import com.example.securitydemo.service.UserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chenxinbao
 * @version 1.0
 * @date 2022/3/28 21:57
 */
@Service
public class UserPermissionServiceImpl implements UserPermissionService {

    @Autowired
    private BsUserComplexMapper bsUserComplexMapper;


    @Override
    public Set<String> getUserPermission(BsUser user) {

        HashSet<String> perms = new HashSet<>();
        perms.addAll(bsUserComplexMapper.selectMenuPermsByUserId(user.getUserId()));
        return perms;
    }
}
