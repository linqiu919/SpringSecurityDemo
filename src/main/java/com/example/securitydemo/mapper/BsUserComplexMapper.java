package com.example.securitydemo.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BsUserComplexMapper {


    List<String> selectMenuPermsByUserId(@Param("userId") Long userId);
}