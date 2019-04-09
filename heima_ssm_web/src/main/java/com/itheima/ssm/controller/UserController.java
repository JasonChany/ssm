package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author: Jason
 * @Date: 2019/4/6 09:50
 * @Description:
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    /**
     * 查询所有用户信息
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<UserInfo> users = userService.findAll();
        mv.addObject("userList",users).setViewName("user-list");
        return mv;
    }

    /**
     * 添加用户
     */
    @RequestMapping("/save.do")
    public String save(UserInfo userInfo){
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    /**
     * 查询用户详情
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam("id") String uid){
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo=userService.findById(uid);
        mv.addObject("user",userInfo).setViewName("user-show");
        return mv;
    }
    /**
     * 获取当前用户信息以及未添加的角色
     */
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam("id") String uid){
        ModelAndView mv = new ModelAndView();
        UserInfo user = userService.findById(uid);
        List<Role> roles=userService.findOtherRoles(uid);
        mv.addObject("user",user).addObject("roleList",roles).setViewName("user-role-add");
        return mv;
    }

    /**
     * 给用户添加角色
     */
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam("userId") String uid,@RequestParam("ids") String[] roleIds){
        userService.addRoleToUser(uid,roleIds);
        return "redirect:findAll.do";
    }
}

