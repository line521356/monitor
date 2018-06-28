package com.dongfang.monitor.controller.permission;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dongfang.monitor.enums.ResourceTypeEnum;
import com.dongfang.monitor.model.Permission;
import com.dongfang.monitor.model.Role;
import com.dongfang.monitor.service.PermissionService;
import com.dongfang.monitor.service.RoleService;
import com.dongfang.monitor.utils.GlobalConstant;
import com.dongfang.monitor.vo.PermissionVo;
import com.dongfang.monitor.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@Controller
public class ManuController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;



    @GetMapping("/admin/permission")
    public String permission(Model model){
        List <Permission> permissionList =  permissionService.getAllParentPermission();
        model.addAttribute("permissionList",permissionList);
        return "/admin/permission";
    }

    @GetMapping("/admin/role")
    public String role(Model model){
            List <Role> roleList = roleService.getAllRole();
            model.addAttribute("roleList",roleList);
        return "/admin/role";
    }

    @GetMapping("/admin/menuAddOrUpdate")
    public String manuAddOrUpdate(Long id, Model model, String type){

        List<Permission> permissionList = permissionService.getAll();
        model.addAttribute("permissionList",permissionList);
        if(type!=null&&type.equals("edit")&&id!=null){
            Permission permission = permissionService.findById(id);
            model.addAttribute("model",permission);
            model.addAttribute("type",type);
        }else if(type!=null&&type.equals("addChild")&&id!=null){
            Permission permission = new Permission();
            Permission parent = new Permission();
            parent.setId(id);
            permission.setParentPermission(parent);
            model.addAttribute("model",permission);
            model.addAttribute("type",type);
        }
        return "/admin/menu-add";
    }

    @PostMapping("/admin/addPermission")
    @ResponseBody
    public JSONObject saveOrUpdateMenu(@RequestBody PermissionVo permissionVo){
        if(permissionService.isNonRepeat(permissionVo)){
            Permission permission = new Permission();
            permission.setResourceType(permissionVo.getResourceType());
            permission.setUrl(permissionVo.getUrl());
            permission.setName(permissionVo.getName());
            permission.setId(permissionVo.getId());
            permission.setAvailable(true);
            permission.setPermission(permissionVo.getPermission());
            if(permissionVo.getParentId()!=null){
                permission.setParentPermission(permissionService.findById(permissionVo.getParentId()));
            }

            permission.setResourceType(permissionVo.getResourceType());
            permissionService.save(permission);
            return GlobalConstant.constructResponse(0,"添加成功",null);

        }else{
            return GlobalConstant.constructResponse(1,"名称或code重复",null);

        }
    }

    @ResponseBody
    @GetMapping("/admin/menuDelete")
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

    @GetMapping("/admin/roleAddOrUpdate")
    public String roleAddOrUpdate(Long id, Model model){
        if(id!=null){
            Role role = roleService.findById(id);
            model.addAttribute("model",role);
        }
        return "/admin/role-add";
    }

    @GetMapping("/admin/getTreePermission")
    @ResponseBody
    public JSONArray getTreePermission(){
        List <Permission> permissionList =  permissionService.getAllParentPermission();
        JSONArray tree = new JSONArray();
        for (Permission permission : permissionList) {
            JSONObject dir = new JSONObject();
            dir.put("value",permission.getId());
            dir.put("title",permission.getName());
            JSONArray childList = new JSONArray();
            for(Permission child:permission.getChildPermission()){
                if(child.getAvailable()==true){
                    JSONObject node = new JSONObject();
                    node.put("value",child.getId());
                    node.put("title",child.getName());
                    node.put("data",new JSONArray());
                    childList.add(node);

                }

            }
            dir.put("data",childList);
            tree.add(dir);
        }
        return tree;
    }

    @PostMapping("/admin/addRole")
    @ResponseBody
    public JSONObject addRole(@RequestBody RoleVo roleVo){
        if(roleService.isNonRepeat(roleVo)){
            Role role = new Role();
            role.setId(roleVo.getId());
            role.setAvailable(true);
            role.setDescription(roleVo.getDescription());
            role.setRole(roleVo.getRole());
            role.setPermissionList(permissionService.findByIds(Arrays.asList(roleVo.getPermissionList())));
            roleService.save(role);
            return GlobalConstant.constructResponse(0,"添加成功",null);
        }else{
            return GlobalConstant.constructResponse(1,"名称重复",null);

        }
    }

    @ResponseBody
    @GetMapping("/admin/roleDelete")
    public JSONObject roleDelete(Long id){
        Role role = roleService.findById(id);
        if(role!=null){
            role.setAvailable(false);
            roleService.save(role);
            return GlobalConstant.constructResponse(0,"删除成功",null);

        }else{
            return GlobalConstant.constructResponse(1,"该角色不存在",null);

        }
    }


}
