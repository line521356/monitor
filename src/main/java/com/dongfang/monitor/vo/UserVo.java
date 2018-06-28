package com.dongfang.monitor.vo;

public class UserVo {

    private Long id;

    private String username;

    private String password;

    private String name;

    private Long [] roleList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long[] getRoleList() {
        return roleList;
    }

    public void setRoleList(Long[] roleList) {
        this.roleList = roleList;
    }
}
