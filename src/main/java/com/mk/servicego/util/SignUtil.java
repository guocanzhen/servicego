package com.mk.servicego.util;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author guocz
 * @date 20210331
 * 签名工具
 */
public class SignUtil {

	/**
	 * 排序参数,签名,用于参数的签名
	 */
	public static String signParams(Map<String, String> needSignParams, String key) {
		String collectParams = needSignParams.entrySet()
				.stream()
				.filter(entry -> entry.getValue() != null && entry.getValue().length() > 0)
				.sorted(Map.Entry.comparingByKey())
				.map(entry -> String.join("=", entry.getKey(), entry.getValue()))
				.collect(Collectors.joining("&"));
		collectParams = collectParams + "&key=" + key;
		return Md5Util.encode(collectParams).toUpperCase();
	}
}
