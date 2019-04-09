package com.itheima.ssm.dao;

import com.itheima.ssm.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Jason
 * @Date: 2019/4/8 12:29
 * @Description:
 *      日志dao接口
 */
@Repository
public interface ISysLogDao {

    /**
     * 插入日志
     * @param sysLog
     */
    @Insert("insert into sysLog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void addSysLog(SysLog sysLog);

    /**
     * 获取所有日志信息
     * @return
     */
    @Select("select *from syslog")
    List<SysLog> findAll();

}
