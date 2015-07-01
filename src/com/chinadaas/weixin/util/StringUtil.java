package com.chinadaas.weixin.util;

import java.util.Random;

/**
 * projectName: weixin<br>
 * desc: TODO<br>
 * date: Dec 3, 2014 12:50:27 PM<br>
 * @author 开发者真实姓名[HuPeng]
 */
public class StringUtil {
	public static String generate() {
		Random random = new Random();
		int codelen = 6;
		String code = "";
		for (int i = 0; i < codelen; i++) {
			code += random.nextInt(10);
		}
		return code;
	}
	
	/** 判断是否为null或空字符 */
	public static boolean isNullOrEmpty(Object o) {
		if(o == null) {
			return true;
		}
		if(String.valueOf(o).replace((char) 12288, ' ').trim().length() == 0) {
			return true;
		}
		if("null".equals(o)) {
			return true;
		}
		return false;
	}
}

