package com.itheima.ssm.service;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: Jason
 * @Date: 2019/4/4 22:28
 * @Description:
 *      用户管理的Service接口，继承SpringSecurity的用户管理接口
 */
public interface IUserService extends UserDetailsService {
    /**
     * 查询所有用户信息
     * @return
     */
    List<UserInfo> findAll();

    /**
     * 添加用户
     * @param userInfo
     */
    void save(UserInfo userInfo);

    /**
     * 查询用户详情
     * @param uid
     * @return
     */
    UserInfo findById(String uid);

    /**
     * 获取用户未添加的角色
     * @param uid
     * @return
     */
    List<Role> findOtherRoles(String uid);

    /**
     * 给用户添加角色
     * @param uid
     * @param roleIds
     */
    void addRoleToUser(String uid, String[] roleIds);
}
