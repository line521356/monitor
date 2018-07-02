package com.dongfang.monitor.service.impl;

import com.dongfang.monitor.dao.monitor.RoleRepository;
import com.dongfang.monitor.model.monitor.Role;
import com.dongfang.monitor.service.RoleService;
import com.dongfang.monitor.vo.RoleVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    RoleRepository roleRepository;


    @Override
    public Boolean isNonRepeat(RoleVo roleVo) {
        if(roleVo.getId()==null&&roleRepository.countByRole(roleVo.getRole())>0){
            return false;
        }
        return true;
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRole() {
        return roleRepository.findByAvailable(true);
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findOne(id);
    }

    @Override
    public List<Role> findByIds(List<Long> ids) {
        return roleRepository.findAll(ids);
    }

    @Override
    public List<Role> searchByRole(String role) {
        return roleRepository.findByRoleLikeAndAvailable("%"+role+"%",true);
    }
}
