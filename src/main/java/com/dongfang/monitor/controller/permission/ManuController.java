package com.dongfang.monitor.controller.permission;

import com.alibaba.fastjson.JSONObject;
import com.dongfang.monitor.enums.ResourceTypeEnum;
import com.dongfang.monitor.model.Permission;
import com.dongfang.monitor.service.PermissionService;
import com.dongfang.monitor.utils.GlobalConstant;
import com.dongfang.monitor.vo.PermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ManuController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/permission")
    public String permission(Model model){
        List <Permission> permissionList =  permissionService.getAllParentPermission();
        model.addAttribute("permissionList",permissionList);
        return "/admin/permission";
    }

    @GetMapping("/role")
    public String role(){
        return "/admin/role";
    }

    @GetMapping("/user")
    public String user(){
        return "/admin/user";
    }

    @GetMapping("/menuAddOrUpdate")
    public String manuAddOrUpdate(Long id,Long parentId,Model model,String type){

        List<Permission> permissionList = permissionService.getAll();
        model.addAttribute("permissionList",permissionList);
        model.addAttribute("id",id);
        model.addAttribute("parentId",parentId);
        model.addAttribute("type",type);
        return "/admin/menu-add";
    }

    @PostMapping("/addPermission")
    @ResponseBody
    public JSONObject saveOrUpdateMenu(@RequestBody PermissionVo permissionVo){
        if(permissionService.isNonRepeat(permissionVo)){
            Permission permission = new Permission();
            permission.setResourceType(permissionVo.getResourceType());
            permission.setUrl(permissionVo.getUrl());
            permission.setName(permissionVo.getName());
            permission.setId(permissionVo.getId());
            permission.setAvailable(true);
            permission.setParentPermission(permissionService.findById(permissionVo.getParentId()));
            permissionService.save(permission);
            return GlobalConstant.constructResponse(0,"添加成功",null);
        }else{
            return GlobalConstant.constructResponse(1,"名称或code重复",null);
        }
    }

    @ResponseBody
    @GetMapping("/menuDelete")
    public JSONObject menuDelete(Long id){
        Permission permission = permissionService.findById(id);
        if(permission!=null){
            permission.setAvailable(false);
            permissionService.save(permission);
            return GlobalConstant.constructResponse(0,"删除成功",null);
        }else{
            return GlobalConstant.constructResponse(1,"该按钮不存在",null);
        }

    }

}
