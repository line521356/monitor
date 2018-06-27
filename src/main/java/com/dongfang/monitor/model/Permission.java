package com.dongfang.monitor.model;

import com.dongfang.monitor.enums.ResourceTypeEnum;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "permission")
public class Permission implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "resource_type")
    private ResourceTypeEnum resourceType;

    @Column(name = "url")
    private String url;

    @Column(name = "permission")
    private String permission;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "parent_id",nullable = true)
    private Permission parentPermission;

    @Column(name = "available")
    private Boolean available = Boolean.FALSE;

    @ManyToMany
    @JoinTable(name="role_permission",joinColumns={@JoinColumn(name="permission_id")},inverseJoinColumns={@JoinColumn(name="role_id")})
    private List<Role> roles;

    @OneToMany(targetEntity = Permission.class, cascade = { CascadeType.ALL }, mappedBy = "parentPermission")
    @Fetch(FetchMode.SUBSELECT)
    private List <Permission> childPermission;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResourceTypeEnum getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceTypeEnum resourceType) {
        this.resourceType = resourceType;
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

    public Permission getParentPermission() {
        return parentPermission;
    }

    public void setParentPermission(Permission parentPermission) {
        this.parentPermission = parentPermission;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Permission> getChildPermission() {
        return childPermission;
    }

    public void setChildPermission(List<Permission> childPermission) {
        this.childPermission = childPermission;
    }
}
