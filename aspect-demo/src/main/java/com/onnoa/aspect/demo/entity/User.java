package com.onnoa.aspect.demo.entity;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/7/28 11:12
 */
public class User {
    Integer id;
    String name;
    String password;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
