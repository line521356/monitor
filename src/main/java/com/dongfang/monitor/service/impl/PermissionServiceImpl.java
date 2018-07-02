package com.dongfang.monitor.service.impl;

import com.dongfang.monitor.dao.PermissionRepository;
import com.dongfang.monitor.dao.UserRepository;
import com.dongfang.monitor.enums.ResourceTypeEnum;
import com.dongfang.monitor.model.Permission;
import com.dongfang.monitor.model.Role;
import com.dongfang.monitor.model.User;
import com.dongfang.monitor.service.PermissionService;
import com.dongfang.monitor.service.UserService;
import com.dongfang.monitor.utils.GlobalConstant;
import com.dongfang.monitor.vo.PermissionVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.*;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    PermissionRepository permissionRepository;

    @Resource
    UserRepository userRepository;

    @Override
    public List<Permission> getAllParentPermission() {
        return permissionRepository.findByResourceTypeInAndAvailable(ResourceTypeEnum.getTopList(), true);
    }

    @Override
    public void save(Permission permission) {
        permissionRepository.save(permission);
    }

    @Override
    public Boolean isNonRepeat(PermissionVo permissionVo) {
        if((permissionVo.getId()==null||permissionVo.getId()==0)&&(permissionRepository.countByAvailableAndPermission(true,permissionVo.getPermission())>0
                ||permissionRepository.countByAvailableAndName(true,permissionVo.getName())>0)){
            return false;
        }
        return true;
    }

    @Override
    public Permission findById(Long id) {
        return permissionRepository.findOne(id);
    }

    @Override
    public List<Permission> findByIds(List<Long> ids) {
        return permissionRepository.findAll(ids);
    }

    @Override
    public List<Permission> getAll() {
        return permissionRepository.findByAvailable(true);
    }

    @Override
    public List<Permission> findUserPromission(User user) {
        if(user.getName().equals(GlobalConstant.ADMIN_ACCOUNT)){
            List <Permission> parentList = permissionRepository.findByResourceTypeInAndAvailable(ResourceTypeEnum.getTopList(),true);
            for (Permission parent : parentList) {
                List <Permission> newChild = new ArrayList<>();
                List <Permission> childList = parent.getChildPermission();
                for (Permission child : childList) {
                    if(child.getAvailable()){
                        newChild.add(child);
                    }
                }
                parent.setChildPermission(newChild);

            }
            return parentList;
        }
        User userModel = userRepository.findOne(user.getId());
        List<Role>roleList = userModel.getRoleList();
        Set<Permission> childPermission = new HashSet<>();
        for (Role role : roleList) {
            childPermission.addAll(role.getPermissionList());
        }
        HashMap<String,Permission>parentPermissionMap = new HashMap<>();
        HashMap<String,Permission>childPermissionMap = new HashMap<>();
        for (Permission permission : childPermission) {
            if(permission.getAvailable()){
                if(permission.getParentPermission()!=null){
                    parentPermissionMap.put(permission.getParentPermission().getId()+"",permission.getParentPermission());
                    childPermissionMap.put(permission.getParentPermission().getId()+"_"+permission.getId(),permission);

                }else{
                    parentPermissionMap.put(permission.getId()+"",permission);

                }


            }

        }
        for(Map.Entry<String, Permission> entry : parentPermissionMap.entrySet()){
            Permission parent = entry.getValue();
            String key = entry.getKey();
            List <Permission> child = new ArrayList<>();
            if(entry.getValue().getResourceType()==ResourceTypeEnum.TOPMENU){
                parentPermissionMap.put(key,parent);
            }
            for(Map.Entry<String, Permission> childEntry :childPermissionMap.entrySet()){
                if(childEntry.getKey().split("_")[0].equals(key)){
                    child.add(childEntry.getValue());
                }
            }
            parent.setChildPermission(child);
            parentPermissionMap.put(key,parent);
        }
        List <Permission> end = new ArrayList<>();
        for(Map.Entry<String, Permission> entry : parentPermissionMap.entrySet()){
            end.add(entry.getValue());
        }
        return end;
    }

    @Override
    public List<Permission> searchByName(String name) {
        List <Permission> permissionList = permissionRepository.findByNameLikeAndAvailable("%"+name+"%",true);
        List <Permission> parentPermissionList = new ArrayList<>();
        Map <Long,Permission> map = new HashMap<>();
        for (Permission permission : permissionList) {
            if(permission.getParentPermission()==null){
                map.put(permission.getId(),permission);
            }else{
                map.put(permission.getParentPermission().getId(),permission.getParentPermission());
            }
        }
        for(Map.Entry<Long,Permission> entry:map.entrySet()){
            parentPermissionList.add(entry.getValue());
        }
        return parentPermissionList;
    }
}
