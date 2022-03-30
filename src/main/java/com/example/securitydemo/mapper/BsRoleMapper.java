package com.example.securitydemo.mapper;

import com.example.securitydemo.entity.BsRole;
import com.example.securitydemo.entity.BsRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BsRoleMapper {
    int deleteByExample(BsRoleExample example);

    int insert(BsRole record);

    int insertSelective(BsRole record);

    List<BsRole> selectByExample(BsRoleExample example);

    int updateByExampleSelective(@Param("record") BsRole record, @Param("example") BsRoleExample example);

    int updateByExample(@Param("record") BsRole record, @Param("example") BsRoleExample example);
}