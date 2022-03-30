package com.example.securitydemo.mapper;

import com.example.securitydemo.entity.BsUser;
import com.example.securitydemo.entity.BsUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BsUserMapper {
    int deleteByExample(BsUserExample example);

    int insert(BsUser record);

    int insertSelective(BsUser record);

    List<BsUser> selectByExample(BsUserExample example);

    int updateByExampleSelective(@Param("record") BsUser record, @Param("example") BsUserExample example);

    int updateByExample(@Param("record") BsUser record, @Param("example") BsUserExample example);
}