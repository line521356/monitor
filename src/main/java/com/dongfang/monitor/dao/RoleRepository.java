package com.dongfang.monitor.dao;

import com.dongfang.monitor.model.Role;
import com.dongfang.monitor.model.User;
import com.dongfang.monitor.support.dao.reposiotry.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RoleRepository extends BaseRepository<Role, Long>,JpaSpecificationExecutor<Role> {

    Integer countByRole(String role);

    List<Role> findByAvailable(Boolean available);

}
