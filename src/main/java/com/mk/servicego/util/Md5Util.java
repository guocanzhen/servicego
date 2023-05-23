package com.mk.servicego.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author guocz
 * @date 20210331
 * MD5工具
 */
public class Md5Util {

	public static String encode(String source) {
		if (source != null && source.length() != 0) {
			MessageDigest messageDigest = null;

			try {
				messageDigest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException var9) {
				
			}

			byte[] encode = messageDigest.digest(source.getBytes());
			StringBuilder hexString = new StringBuilder();
			byte[] var4 = encode;
			int var5 = encode.length;

			for(int var6 = 0; var6 < var5; ++var6) {
				byte anEncode = var4[var6];
				String hex = Integer.toHexString(255 & anEncode);
				if (hex.length() == 1) {
					hexString.append('0');
				}

				hexString.append(hex);
			}
			return hexString.toString();
		} else {
			return null;
		}
	}

	public Md5Util() {}

}
