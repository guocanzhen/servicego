package com.mk.servicego.config;

import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author guocz
 * @date 2023/4/10 14:04
 * MVC全局特性
 */
public class MvcConfigurer implements WebMvcConfigurer {

    /**
     * 拦截器
     * @param registry 注册
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry){

    }

    /**
     * 跨域访问配置
     * @param registry 注册
     */
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/api/**")
                .allowedOrigins("http://domain2.com")
                .allowedMethods("POST", "GET");
    }

    /**
     * 格式化
     * @param registry 注册
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * URI到视图的映射
     * @param registry 注册
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry){

    }
}
