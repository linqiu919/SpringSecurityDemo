package com.example.securitydemo.mapper;

import com.example.securitydemo.entity.BsMenu;
import com.example.securitydemo.entity.BsMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BsMenuMapper {
    int deleteByExample(BsMenuExample example);

    int insert(BsMenu record);

    int insertSelective(BsMenu record);

    List<BsMenu> selectByExample(BsMenuExample example);

    int updateByExampleSelective(@Param("record") BsMenu record, @Param("example") BsMenuExample example);

    int updateByExample(@Param("record") BsMenu record, @Param("example") BsMenuExample example);
}