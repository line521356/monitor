package com.dongfang.monitor.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.dongfang.monitor.enums.UserStateEnum;
import com.dongfang.monitor.model.Role;
import com.dongfang.monitor.model.User;
import com.dongfang.monitor.service.RoleService;
import com.dongfang.monitor.service.UserService;
import com.dongfang.monitor.utils.GlobalConstant;
import com.dongfang.monitor.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/admin/user")
    public String user(Model model,String username){
        List<User> userList = null;
        if(StringUtils.isNotEmpty(username)){
            userList = userService.searchUserByUsername(username);
        }else{
            userList = userService.getAllUser();
        }
        model.addAttribute("userList",userList);
        return "admin/user";
    }

    @GetMapping("/admin/userAddOrUpdate")
    public String userAddOrUpdate(Long id, Model model){
        if(id!=null){
            User user = userService.findById(id);
            model.addAttribute("model",user);
        }
        model.addAttribute("roleList",roleService.getAllRole());
        return "admin/user-add";

    }

    @PostMapping("/admin/addUser")
    @ResponseBody
    public JSONObject addUser(@RequestBody UserVo userVo){
        if(userService.isNonRepeat(userVo)){
            User user = null;
            if(userVo.getId()!=null){
                user = userService.findById(userVo.getId());
                user.setUsername(userVo.getUsername());
                if(userVo.getPassword()!=null&&!userVo.getPassword().trim().equals("")){
                    user.setPassword(userVo.getPassword());
                }
                user.setName(userVo.getName());
                user.setSalt(UUID.randomUUID().toString());
                user.setState(UserStateEnum.ACTIVATE);
                user.setRoleList(roleService.findByIds(Arrays.asList(userVo.getRoleList())));
            }else{
                user = new User();
                user.setUsername(userVo.getUsername());
                user.setPassword(userVo.getPassword());
                user.setName(userVo.getName());
                user.setSalt(UUID.randomUUID().toString());
                user.setState(UserStateEnum.ACTIVATE);
                user.setRoleList(roleService.findByIds(Arrays.asList(userVo.getRoleList())));
            }
            userService.save(user);
            return GlobalConstant.constructResponse(0,"添加成功",null);
        }else{
            return GlobalConstant.constructResponse(1,"用户名重复",null);
        }
    }

    @GetMapping("/admin/userDelete")
    @ResponseBody
    public JSONObject userDelete(Long id){
        User user = userService.findById(id);
        if(user!=null){
            user.setState(UserStateEnum.INACTIVATION);
            userService.save(user);
            return GlobalConstant.constructResponse(0,"删除成功",null);

        }else{
            return GlobalConstant.constructResponse(1,"该账号不存在",null);

        }
    }
}
