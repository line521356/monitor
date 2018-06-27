package com.dongfang.monitor.service.impl;

import com.dongfang.monitor.dao.PermissionRepository;
import com.dongfang.monitor.enums.ResourceTypeEnum;
import com.dongfang.monitor.model.Permission;
import com.dongfang.monitor.service.PermissionService;
import com.dongfang.monitor.vo.PermissionVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    PermissionRepository permissionRepository;

    @Override
    public List<Permission> getAllParentPermission() {
        return permissionRepository.findByResourceTypeAndAvailable(ResourceTypeEnum.MENU, true);
    }

    @Override
    public void save(Permission permission) {
        permissionRepository.save(permission);
    }

    @Override
    public Boolean isNonRepeat(PermissionVo permissionVo) {
        if(permissionRepository.countByAvailableAndPermission(true,permissionVo.getPermission())>0
                ||permissionRepository.countByAvailableAndName(true,permissionVo.getName())>0){
            return false;
        }
        return true;
    }

    @Override
    public Permission findById(Long id) {
        return permissionRepository.findOne(id);
    }

    @Override
    public List<Permission> getAll() {
        return permissionRepository.findByAvailable(true);
    }
}
