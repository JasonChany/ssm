package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author: Jason
 * @Date: 2019/4/6 14:47
 * @Description:
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;
    /**
     * 查询所有角色信息
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Role> roles=roleService.findAll();
        mv.addObject("roleList",roles).setViewName("role-list");
        return mv;
    }

    /**
     * 添加角色信息
     */
    @RequestMapping("/save.do")
    public String save(Role role){
        roleService.save(role);
        return "redirect:findAll.do";
    }

    /**
     * 删除角色
     */
    @RequestMapping("/delete.do")
    public String delete(String[] ids){
        roleService.delete(ids);
        return "redirect:findAll.do";
    }
    /**
     * 获取角色信息以及未添加的权限信息
     */
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam("id") String rid){
        ModelAndView mv = new ModelAndView();
        Role role=roleService.findById(rid);
        List<Permission> permissions=roleService.findOtherPermissionsByRoleId(rid);
        mv.addObject("role",role).addObject("permissionList",permissions).setViewName("role-permission-add");
        return mv;
    }

    /**
     *给角色添加权限
     */
    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(String roleId,@RequestParam("ids") String[] permissionIds){
        roleService.addPermissionToRole(roleId,permissionIds);
        return "redirect:findAll.do";
    }
}
