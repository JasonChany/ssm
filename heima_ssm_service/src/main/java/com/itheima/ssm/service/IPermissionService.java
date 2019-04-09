package com.itheima.ssm.service;

import com.itheima.ssm.domain.Permission;

import java.util.List;

/**
 * @Author: Jason
 * @Date: 2019/4/6 18:12
 * @Description:
 *      权限管理service接口
 */
public interface IPermissionService {

    /**
     * 获取所有权限信息
     * @return
     */
    List<Permission> findAll();

    /**
     * 添加权限
     * @param permission
     */
    void save(Permission permission);
}
