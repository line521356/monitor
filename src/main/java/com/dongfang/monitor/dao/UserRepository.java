package com.dongfang.monitor.dao;

import com.dongfang.monitor.enums.UserStateEnum;
import com.dongfang.monitor.model.User;
import com.dongfang.monitor.support.dao.reposiotry.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends BaseRepository<User, Long>,JpaSpecificationExecutor<User> {

    User findByUsername(String username);

    List<User> findByState(UserStateEnum userStateEnum);

    Integer countByUsernameAndState(String username,UserStateEnum userStateEnum);

}
