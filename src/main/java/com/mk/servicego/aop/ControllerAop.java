package com.mk.servicego.aop;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author guocz
 * @date 2023/4/10 14:26
 */
@Component
@Log4j2
@Aspect
@Order(1)
public class ControllerAop {

    /**
     * 计算本次请求耗时用
     */
    private final ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(* com.mk.servicego.*.*Controller.*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object allControllerHandler(final ProceedingJoinPoint pjp) throws Throwable{
        // 调用前处理
        globalLogBefore(pjp);

        // 调用原有的方法，o为原方法返回对象
        log.info("controller方法-开始执行");
        Object o = pjp.proceed();
        log.info("controller方法-执行结束");

        // 调完后
        globalLogAfter(o);

        return o;
    }

    /**
     * 全局日志
     */
    private void globalLogBefore(ProceedingJoinPoint pjp) {
        // 记录请求时间
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        log.info("全局日志-请求URL: {}", request.getRequestURI());
        log.info("全局日志-请求URL参数: {}", request.getQueryString());
        log.info("全局日志-请求方式: {}", request.getMethod());
        log.info("全局日志-请求数据类型: {}", request.getContentType());
        log.info("全局日志-请求数据编码: {}", request.getCharacterEncoding());
        log.info("全局日志-请求IP: {}", request.getRemoteAddr());
        log.info("全局日志-请求包名.方法名: {}", pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName());
        log.info("全局日志-请求参数: {}", Arrays.toString(pjp.getArgs()));
    }

    /**
     * 全局日志
     */
    private void globalLogAfter(Object o) {
        // 处理完请求，响应内容
        log.info("全局日志-响应参数: {}", JSONUtil.toJsonStr(o));
        // 本次请求时间
        log.info("全局日志-本次请求时间: {}", LocalDateTimeUtil.format(LocalDateTimeUtil.of(startTime.get()),"yyyy-MM-dd HH:mm:ss:ms"));
        long retLong = System.currentTimeMillis();
        log.info("全局日志-本次响应时间: {}", LocalDateTimeUtil.format(LocalDateTimeUtil.of(retLong), "yyyy-MM-dd HH:mm:ss:ms"));
        // 记录本次请求时间
        long mss = retLong - startTime.get();
        log.info("全局日志-本次请求耗时: {}ms", mss);
        // 使用完清除当前线程存储时间，避免内存泄漏
        startTime.remove();
    }
}
