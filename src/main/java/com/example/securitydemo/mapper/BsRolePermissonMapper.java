package com.example.securitydemo.mapper;

import com.example.securitydemo.entity.BsRolePermisson;
import com.example.securitydemo.entity.BsRolePermissonExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BsRolePermissonMapper {
    int deleteByExample(BsRolePermissonExample example);

    int insert(BsRolePermisson record);

    int insertSelective(BsRolePermisson record);

    List<BsRolePermisson> selectByExample(BsRolePermissonExample example);

    int updateByExampleSelective(@Param("record") BsRolePermisson record, @Param("example") BsRolePermissonExample example);

    int updateByExample(@Param("record") BsRolePermisson record, @Param("example") BsRolePermissonExample example);
}