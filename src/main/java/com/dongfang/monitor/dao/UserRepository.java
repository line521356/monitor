package com.dongfang.monitor.dao;

import com.dongfang.monitor.model.User;
import com.dongfang.monitor.support.dao.reposiotry.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Integer>,JpaSpecificationExecutor<User> {

    User findByUsername(String username);

}
