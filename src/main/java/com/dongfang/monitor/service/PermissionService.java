package com.dongfang.monitor.service;

import com.dongfang.monitor.model.monitor.Permission;
import com.dongfang.monitor.model.monitor.User;
import com.dongfang.monitor.vo.PermissionVo;

import java.util.List;

public interface PermissionService {

    /**
     * 查询一级菜单
     * @return
     */
    List<Permission> getAllParentPermission();

    void save(Permission permission);

    Boolean isNonRepeat(PermissionVo permissionVo);

    Permission findById(Long id);

    List <Permission> findByIds(List <Long> ids);

    List <Permission> getAll();

    List <Permission> findUserPromission(User user);

    List <Permission> searchByName(String name);
}
