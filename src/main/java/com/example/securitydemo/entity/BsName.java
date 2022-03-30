/**
* BsName.java
*
* @author chenxb
* @date 2022-03-28 09:17:51
* @company 杭州震墨科技有限公司
*/
package com.example.securitydemo.entity;

import lombok.Data;

@Data
public class BsName {
    /**
     * 主键
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     *  设置属性并返回对象
     *
     * @param id 主键
     * @return BsName
     */
    public BsName withId(Long id) {
        this.setId(id);
        return this;
    }

    /**
     *  设置属性并返回对象
     *
     * @param name name
     * @return BsName
     */
    public BsName withName(String name) {
        this.setName(name);
        return this;
    }
}