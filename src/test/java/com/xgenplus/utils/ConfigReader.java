package com.xgenplus.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
	private static Properties prop;

	public static void loadConfig() {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/resources/config.properties");
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String get(String key) {
		if (prop == null) {
			loadConfig();
		}
		return prop.getProperty(key);
	}
}
