package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Jason
 * @Date: 2019/4/6 12:54
 * @Description:
 *      权限管理dao接口
 */
@Repository
public interface IPermissionDao {

    /**
     * 根据角色ID查询对应的权限信息
     */
    @Select("Select *\n" +
            "  From Permission\n" +
            " Where Id In (Select PermissionId From Role_Permission Where RoleId = #{rid})")
    List<Permission> findPermissionsByRoleId(String rid);

    /**
     * 获取所有权限信息
     * @return
     */
    @Select("select *from permission")
    List<Permission> findAll();

    /**
     * 添加权限
     * @param permission
     */
    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission);
}
