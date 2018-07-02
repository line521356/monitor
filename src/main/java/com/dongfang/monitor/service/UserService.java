package com.dongfang.monitor.service;

import com.dongfang.monitor.model.User;
import com.dongfang.monitor.vo.UserVo;

import java.util.List;

public interface UserService {

    User findByUsername(String username);

    List <User> searchUserByUsername(String username);

    void save(User user);

    User findById(Long id);

    List<User> getAllUser();

    Boolean isNonRepeat(UserVo userVo);
}
