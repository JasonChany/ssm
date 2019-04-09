package com.itheima.ssm.service;

import com.itheima.ssm.domain.SysLog;

import java.util.List;

/**
 * @Author: Jason
 * @Date: 2019/4/8 12:23
 * @Description:
 *      日志Service接口
 */
public interface ISysLogService {
    /**
     * 插入日志
     */
    void addSysLog(SysLog sysLog);

    /**
     * 获取所有日志信息
     * @return
     */
    List<SysLog> findAll();

}
