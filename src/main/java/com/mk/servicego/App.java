package com.mk.servicego;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author guocz
 * @date 2023/4/10 10:00
 */
@SpringBootApplication
@MapperScan({"com.mk.servicego.**.mapper"})
@EnableSwagger2
@EnableAspectJAutoProxy(exposeProxy = true)
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
