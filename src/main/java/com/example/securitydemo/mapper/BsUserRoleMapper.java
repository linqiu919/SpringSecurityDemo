/*
*
* BsUserRoleMapper.java
* Copyright(C) 2017-2020 fendo公司
* @date 2022-03-28
*/
package com.example.securitydemo.mapper;

import com.example.securitydemo.entity.BsUserRole;
import com.example.securitydemo.entity.BsUserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BsUserRoleMapper {
    int deleteByExample(BsUserRoleExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(BsUserRole record);

    int insertSelective(BsUserRole record);

    List<BsUserRole> selectByExample(BsUserRoleExample example);

    BsUserRole selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") BsUserRole record, @Param("example") BsUserRoleExample example);

    int updateByExample(@Param("record") BsUserRole record, @Param("example") BsUserRoleExample example);

    int updateByPrimaryKeySelective(BsUserRole record);

    int updateByPrimaryKey(BsUserRole record);
}