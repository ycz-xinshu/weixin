package com.chinadaas.weixin.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;


/**
 * projectName: weixin<br>
 * desc: TODO<br>
 * date: Dec 23, 2014 10:45:15 AM<br>
 * @author 开发者真实姓名[HuPeng]
 */
public class PropertiesUtil {
	private static String webInfoPath = new File(PropertiesUtil.class.getResource("/").getFile()).getAbsolutePath().split("WEB-INF")[0]+"WEB-INF/";
	
	private static Properties PROPS = new Properties();
	
	static {
		try {
			String configPath = webInfoPath + "config/";
			File configDir = new File(configPath);
			Properties prop = new Properties();
			if (configDir.exists()) {
				for (File configFile : configDir.listFiles()) {
					InputStream is = new FileInputStream(configFile);
					prop.load(is);
					PROPS.putAll(prop);
					prop.clear();
				}
			}
		} catch (Exception e) {
			System.err.println("load properties failed");
		}
	}
	
	public static String getProperty(String key, String... defaultValue) {
		if (defaultValue.length > 0) {
			return PROPS.getProperty(key, defaultValue[0]).toString();
		} else {
			return PROPS.getProperty(key).toString();
		}
	}

}

