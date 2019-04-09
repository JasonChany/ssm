package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.IUserDao;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Jason
 * @Date: 2019/4/4 22:31
 * @Description:
 */
@Service("userService")
@Transactional
public class IUserServiceImpl implements IUserService {
    @Autowired
    private IUserDao iUserDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * 封装成框架能识别的角色对象
     * @param roles
     * @return
     */
    public static List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> authorities=new ArrayList<SimpleGrantedAuthority>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return authorities;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //从数据库获取实际存储的用户对象
        UserInfo userInfo = iUserDao.findUserByUsername(username);
        //给框架返回其能识别的User对象（UserDetails的实现类对象）,这个User是框架预定义对象，不是自定义的
        User user = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()==0?false:true,true,true,true, getAuthority(userInfo.getRoles()));
        return user;
    }

    @Override
    public List<UserInfo> findAll() {
        return iUserDao.findAll();
    }

    @Override
    public void save(UserInfo userInfo) {
        //存储前将密码进行加密处理
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        iUserDao.save(userInfo);
    }

    @Override
    public UserInfo findById(String uid) {
        return iUserDao.findById(uid);
    }

    @Override
    public List<Role> findOtherRoles(String uid) {
        return iUserDao.findOtherRoles(uid);
    }

    @Override
    public void addRoleToUser(String uid, String[] roleIds) {
        for (String roleId : roleIds) {
            iUserDao.addRoleToUser(uid,roleId);
        }
    }
}
