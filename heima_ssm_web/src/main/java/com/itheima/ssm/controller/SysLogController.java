package com.itheima.ssm.controller;

import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author: Jason
 * @Date: 2019/4/8 11:21
 * @Description:
 */
@Controller
@RequestMapping("/sysLog")
public class SysLogController {
    @Autowired
    private ISysLogService sysLogService;
    /**
     * 获取所有访问日志信息
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        List<SysLog> sysLogs=sysLogService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("sysLogs",sysLogs).setViewName("syslog-list");
        return mv;
    }
}
