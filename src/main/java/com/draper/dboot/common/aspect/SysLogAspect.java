package com.draper.dboot.common.aspect;

import com.alibaba.fastjson.JSONObject;
import com.draper.dboot.common.annotation.SysLog;
import com.draper.dboot.common.utils.HttpContextUtils;
import com.draper.dboot.common.utils.IPUtils;
import com.draper.dboot.system.entity.beans.SysLogEntity;
import com.draper.dboot.system.entity.beans.SysUser;
import com.draper.dboot.system.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author draper_hxy
 */
@Slf4j
@Aspect
@Component
public class SysLogAspect {

    @Autowired
    private SysLogService sysLogService;

    @Pointcut("@annotation(com.draper.dboot.common.annotation.SysLog)")
    public void logPointcut() {
    }

    @Around("logPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long consumeTime = System.currentTimeMillis() - startTime;

        // 保存系统日志
        saveLog(joinPoint, consumeTime);
        return result;
    }

    /**
     * 封装 SysLogEntity，并保存
     */
    private void saveLog(ProceedingJoinPoint joinPoint, Long consumeTime) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        SysLogEntity entity = new SysLogEntity();

        // 1. 操作信息
        SysLog sysLog = method.getAnnotation(SysLog.class);
        if (sysLog != null) {
            entity.setOperation(sysLog.operation());
        }

        // 2. 请求方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        entity.setRequestMethod(className + "." + methodName + "()");

        // 3. ip
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        entity.setIp(IPUtils.getIpAddr(request));

        // 4. 请求参数
        wrapperParams(joinPoint, entity, request.getRequestURI());

        // 5. 用户名
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        if (sysUser != null) {
            entity.setUsername(sysUser.getUsername());
        }

        // 6. 耗时
        entity.setConsumeTime(consumeTime);

        // 7. 创建时间
        entity.setCreateTime(LocalDateTime.now());

        sysLogService.saveLog(entity);
    }

    private void wrapperParams(ProceedingJoinPoint joinPoint, SysLogEntity entity, String url) {
        if (!filterParams().contains(url)) {
            Object[] args = joinPoint.getArgs();
            String params = JSONObject.toJSONString(args);
            entity.setRequestParams(params);
        }
    }

    public Set<String> filterParams() {
        Set<String> filterSet = new HashSet<>();
        filterSet.add("/api/login");
        filterSet.add("/api/user");
        return filterSet;
    }

}
