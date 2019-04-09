package com.itheima.ssm.service;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Jason
 * @Date: 2019/4/6 14:51
 * @Description:
 *      角色管理的service接口
 */

public interface IRoleService {

    /**
     * 查询所有角色信息
     * @return
     */
    List<Role> findAll();

    /**
     * 添加角色
     * @param role
     */
    void save(Role role);

    /**
     * 删除角色
     * @param ids
     */
    void delete(String[] ids);

    /**
     * 根据角色ID获取角色信息
     * @param rid
     * @return
     */
    Role findById(String rid);

    /**
     * 根据角色ID获取未添加的权限信息
     * @param rid
     * @return
     */
    List<Permission> findOtherPermissionsByRoleId(String rid);

    /**
     * 给角色添加权限
     * @param roleId
     * @param permissionIds
     */
    void addPermissionToRole(String roleId, String[] permissionIds);
}
