package com.dongfang.monitor.service;

import com.dongfang.monitor.model.User;

public interface UserService {

    User findByUsername(String username);

    void save(User user);


}
