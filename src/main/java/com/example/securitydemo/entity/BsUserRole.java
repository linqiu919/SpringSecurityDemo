/*
*
* BsUserRole.java
* Copyright(C) 2017-2020 fendo公司
* @date 2022-03-28
*/
package com.example.securitydemo.entity;

import lombok.Data;

@Data
public class BsUserRole {
    private Long userId;

    private Integer roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}