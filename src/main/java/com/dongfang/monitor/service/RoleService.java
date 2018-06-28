package com.dongfang.monitor.service;

import com.dongfang.monitor.model.Role;
import com.dongfang.monitor.vo.RoleVo;

import java.util.List;

public interface RoleService {

    Boolean isNonRepeat(RoleVo roleVo);

    void save(Role role);

    List<Role> getAllRole();

    Role findById(Long id);

    List <Role> findByIds(List <Long> ids);
}
