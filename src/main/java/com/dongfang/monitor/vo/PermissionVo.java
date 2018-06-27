package com.dongfang.monitor.vo;

import com.dongfang.monitor.enums.ResourceTypeEnum;
import sun.awt.SunHints;

import javax.validation.constraints.NotNull;

public class PermissionVo {

    private Long id;

    private Long parentId;

    @NotNull(message = "权限名不能为空")
    private String name;

    @NotNull(message = "url不能为空")
    private String url;

    @NotNull(message = "权限code不能为空")
    private String permission;

    @NotNull(message = "权限类型不能为空")
    private ResourceTypeEnum resourceType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public ResourceTypeEnum getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceTypeEnum resourceType) {
        this.resourceType = resourceType;
    }
}
