package com.example.securitydemo;

import com.example.securitydemo.entity.BsUser;
import com.example.securitydemo.entity.BsUserExample;
import com.example.securitydemo.mapper.BsUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author chenxinbao
 * @version 1.0
 * @date 2022/3/28 21:19
 */
@SpringBootTest
public class SecurityTest {

    @Autowired
    private BsUserMapper bsUserMapper;


    @Test
    public void selectBsUserByNameTest(){
        BsUserExample example = new BsUserExample();
        BsUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo("student001");
        List<BsUser> bsUsers = bsUserMapper.selectByExample(example);
        bsUsers.forEach(System.out::println);
    }
}
