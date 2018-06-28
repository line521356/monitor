package com.dongfang.monitor.vo;

import java.util.List;

public class RoleVo {

    private Long id;

    private String role;

    private String description;

    private Long [] permissionList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long[] getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(Long[] permissionList) {
        this.permissionList = permissionList;
    }
}
