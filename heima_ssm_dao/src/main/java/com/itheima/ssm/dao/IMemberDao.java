package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Author: Jason
 * @Date: 2019/4/3 17:06
 * @Description:
 */
@Repository
public interface IMemberDao {
    /**
     * 根据ID查询会员信息
     * @param id
     * @return
     */

    @Select("select *from member where id=#{id}")
    Member findById(String id);
}
