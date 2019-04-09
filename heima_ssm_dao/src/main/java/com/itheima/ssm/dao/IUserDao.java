package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Jason
 * @Date: 2019/4/4 22:32
 * @Description:
 *      用户管理Dao接口
 */
@Repository
public interface IUserDao {

    /**
     * 根据用户名获取用户信息（包含对应的角色信息）
     * @param username
     * @return
     */
    @Select("select *from users where username=#{username}")
    @Results(id = "userMap",value = {
            @Result(id = true,property ="id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = List.class,
                    many = @Many(select = "com.itheima.ssm.dao.IRoleDao.findRolesByUserId"))
    })
    UserInfo findUserByUsername(String username);

    /**
     * 查询所有用户信息
     */
    @Select("select *from users")
    List<UserInfo> findAll();

    /**
     * 添加用户
     * @param userInfo
     */
    @Insert("insert into users(EMAIL,USERNAME,PASSWORD,PHONENUM,STATUS) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo);

    /**
     * 查询用户详情
     * @param uid
     * @return
     */
    @Select("select *from users where id=#{uid}")
    @ResultMap("userMap")
    UserInfo findById(String uid);

    /**
     * 获取当前用户未添加的角色
     * @param uid
     * @return
     */
    @Select("select *from role where id not in(select roleid from users_role where userid=#{uid})")
    List<Role> findOtherRoles(String uid);

    /**
     * 给用户添加角色
     * @param uid
     * @param roleId
     */
    @Insert("insert into users_role values(#{uid},#{roleId})")
    void addRoleToUser(@Param("uid") String uid, @Param("roleId") String roleId);
}
