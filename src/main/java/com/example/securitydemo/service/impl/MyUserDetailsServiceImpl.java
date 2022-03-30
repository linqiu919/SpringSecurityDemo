package com.example.securitydemo.service.impl;

import com.example.securitydemo.common.utils.EmptyUtils;
import com.example.securitydemo.entity.BsRolePermissonExample;
import com.example.securitydemo.entity.BsUser;
import com.example.securitydemo.entity.BsUserExample;
import com.example.securitydemo.entity.LoginUser;
import com.example.securitydemo.mapper.BsRolePermissonMapper;
import com.example.securitydemo.mapper.BsUserMapper;
import com.example.securitydemo.service.UserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author chenxinbao
 * @version 1.0
 * @date 2022/3/28 14:20
 */
@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

    private static final String USERNAME = "test";
    private static final String PASSWORD = "test";

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private BsUserMapper bsUserMapper;

    @Autowired
    private UserPermissionService userPermissionService;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if(!USERNAME.equals(username)){
//            throw new UsernameNotFoundException("用户名不存在");
//        }
//        UserDetails user = new User(USERNAME, bCryptPasswordEncoder.encode(PASSWORD), AuthorityUtils.commaSeparatedStringToAuthorityList("admin,common"));
//        return user;
        if(EmptyUtils.isBlank(username)){
            throw new UsernameNotFoundException("用户名不能为空");
        }
        BsUserExample example = new BsUserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<BsUser> user = bsUserMapper.selectByExample(example);
        BsUser bsUser = user.get(0);
        if(null == bsUser){
            throw new UsernameNotFoundException("用户不存在");
        }
//        return new User(bsUser.getUsername(),bsUser.getPassword(),AuthorityUtils.NO_AUTHORITIES );
        return createLoginUser(bsUser,userPermissionService.getUserPermission(bsUser));
    }


    public UserDetails createLoginUser(BsUser user, Set<String> permissions){
        permissions.forEach(System.out::println);
        return new LoginUser(user,permissions);
    }
}
