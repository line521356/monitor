package com.dongfang.monitor.service.impl;

import com.dongfang.monitor.dao.UserRepository;
import com.dongfang.monitor.model.User;
import com.dongfang.monitor.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
