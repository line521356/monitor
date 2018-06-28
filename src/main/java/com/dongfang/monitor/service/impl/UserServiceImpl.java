package com.dongfang.monitor.service.impl;

import com.dongfang.monitor.dao.UserRepository;
import com.dongfang.monitor.enums.UserStateEnum;
import com.dongfang.monitor.model.User;
import com.dongfang.monitor.service.UserService;
import com.dongfang.monitor.vo.UserVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findByState(UserStateEnum.ACTIVATE);
    }

    @Override
    public Boolean isNonRepeat(UserVo userVo) {
        if(userVo.getId()==null&&userRepository.countByUsernameAndState(userVo.getUsername(),UserStateEnum.ACTIVATE)>0){
            return false;
        }
        return true;
    }


}
