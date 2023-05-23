package com.mk.servicego.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author guocz
 * @date 20210329
 * 浏览器校验
 */
public class BrowserCheck {

    /**
     * 微信
     */
    public static boolean isWeChat(HttpServletRequest request){
        String userAgent = request.getHeader("user-agent").toLowerCase();
        return userAgent.contains("micromessenger");
    }

    /**
     * 谷歌
     */
    public static boolean isChrome(HttpServletRequest request){
        String userAgent = request.getHeader("user-agent").toLowerCase();
        return userAgent.contains("chrome");
    }

    /**
     * 支付宝
     */
    public static boolean isAliPay(HttpServletRequest request){
        String userAgent = request.getHeader("user-agent").toLowerCase();
        return userAgent.contains("alipayclient");
    }
}
