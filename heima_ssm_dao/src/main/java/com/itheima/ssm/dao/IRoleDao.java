package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Jason
 * @Date: 2019/4/4 22:46
 * @Description:
 *      角色管理Dao接口
 */
@Repository
public interface IRoleDao {

    /**
     * 根据用户ID获取对应的角色信息
     * @param userId
     * @return
     */
    @Select("select *from role where id in" +
            "(select roleid from users_role where userid=#{userId})")
    @Results(id = "roleMap",value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = List.class,
                    many = @Many(select = "com.itheima.ssm.dao.IPermissionDao.findPermissionsByRoleId"))
    })
    List<Role> findRolesByUserId(String userId);

    /**
     * 获取所有角色信息
     * @return
     */
    @Select("select *from role")
    List<Role> findAll();

    /**
     * 添加角色
     * @param role
     */
    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role);

    /**
     * 删除角色
     * @param id
     */
    @Delete("delete from role where id=#{id}")
    void delete(String id);

    /**
     * 根据角色ID获取角色信息
     * @param rid
     * @return
     */
    @Select("select *from role where id=#{rid}")
    Role findById(String rid);

    /**
     * 根据角色ID获取未添加的权限信息
     * @param rid
     * @return
     */
    @Select("select *from permission where id not in(select permissionId from role_permission where roleId=#{rid})")
    List<Permission> findOtherPermissionsByRoleId(String rid);

    /**
     * 给角色添加权限
     * @param roleId
     * @param permissionId
     */
    @Insert("insert into role_permission(permissionId,roleId) values(#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("roleId") String roleId,@Param("permissionId") String permissionId);
}
