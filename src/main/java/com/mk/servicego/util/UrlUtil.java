package com.mk.servicego.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author guocz
 * @date 20210409
 * url工具
 */
public class UrlUtil {

    /**
     * 解析出url参数中的键值对
     * 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
     * @param url  url地址
     * @return  url请求参数部分
     */
    public static Map<String, String> urlSplit(String url){
        Map<String, String> mapRequest = new HashMap<String, String>();
        String[] arrSplit=null;
        String strUrlParam= truncateUrlPage(url);
        if(strUrlParam==null){
            return mapRequest;
        }
        arrSplit=strUrlParam.split("[&]");
        for(String strSplit:arrSplit){
            String[] arrSplitEqual=null;
            arrSplitEqual= strSplit.split("[=]");
            //解析出键值
            if(arrSplitEqual.length>1){
                //正确解析
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
            }else{
                if(!"".equals(arrSplitEqual[0])){
                    //只有参数没有值，不加入
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }
        }
        return mapRequest;
    }

    /**
     * 去掉url中的路径，留下请求参数部分
     * @param strUrl url地址
     * @return url请求参数部分
     */
    private static String truncateUrlPage(String strUrl){
        String strAllParam=null;
        String[] arrSplit=null;
        strUrl=strUrl.trim().toLowerCase();
        arrSplit=strUrl.split("[?]");
        if(strUrl.length()>1){
            if(arrSplit.length>1){
                for (int i=1;i<arrSplit.length;i++){
                    strAllParam = arrSplit[i];
                }
            }
        }
        return strAllParam;
    }

}
