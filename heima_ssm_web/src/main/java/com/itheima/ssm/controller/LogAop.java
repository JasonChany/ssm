package com.itheima.ssm.controller;

import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Author: Jason
 * @Date: 2019/4/8 09:59
 * @Description:
 *      日志通知类
 */
@Component
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ISysLogService sysLogService;
    private Date visitTime;
    private Class visitClazz;
    private Method visitMethod;

    //定义切入点表达式
    @Pointcut("execution(* com.itheima.ssm.controller.*.*(..))")
    public void pt(){}

    //前置通知
    @Before("pt()")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        //访问时间
        visitTime=new Date();
        //获取访问的类
        visitClazz = jp.getTarget().getClass();
        //获取访问的方法名
        String methodName=jp.getSignature().getName();
        //获取访问参数
        Object[] args = jp.getArgs();
        //获取访问的方法对象
        if (args==null||args.length==0){
            //无参方法对象
            visitMethod=visitClazz.getMethod(methodName);
        }else {
            Class[] argsClazz = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                argsClazz[i]=args[i].getClass();
            }
            //有参方法对象
            visitMethod=visitClazz.getMethod(methodName,argsClazz);
        }
    }

    //后置通知
    @AfterReturning("pt()")
    public void doAfterReturning(JoinPoint jp){
        //屏蔽查看日志记录时生成日志信息
        if (visitClazz==SysLogController.class||visitClazz==LogAop.class){
            return;
        }
        //获取执行时长(毫秒)
        Date endTime=new Date();
        Long executionTime=endTime.getTime()-visitTime.getTime();

        //获取操作者
        SecurityContext securityContext = SecurityContextHolder.getContext();
        User user = (User) securityContext.getAuthentication().getPrincipal();
        String username=user.getUsername();

        //获取IP
        String ip = request.getRemoteAddr();

        //获取url
        String url="";
        if (visitClazz!=null&&visitMethod!=null&&visitClazz!=LogAop.class){
            //获取访问类的注解
            RequestMapping requestMappingClazz = (RequestMapping) visitClazz.getAnnotation(RequestMapping.class);
            //获取访问方法的注解
            RequestMapping requestMappingMethod = visitMethod.getAnnotation(RequestMapping.class);
            if (requestMappingClazz!=null&&requestMappingMethod!=null) {
                //获取访问类的注解值
                String firstUrl = requestMappingClazz.value()[0];
                //获取访问方法的注解值
                String secondUrl = requestMappingMethod.value()[0];
                //拼接url
                url=firstUrl+secondUrl;
            }
        }

        //封装成日志对象
        SysLog sysLog = new SysLog();
        sysLog.setVisitTime(visitTime);
        sysLog.setUsername(username);
        sysLog.setIp(ip);
        sysLog.setUrl(url);
        sysLog.setExecutionTime(executionTime);
        sysLog.setMethod("[类名]"+visitClazz.getName()+"[方法名]"+visitMethod.getName());

        //插入日志
        sysLogService.addSysLog(sysLog);
    }
}
