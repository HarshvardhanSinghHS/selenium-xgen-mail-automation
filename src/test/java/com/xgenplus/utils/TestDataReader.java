package com.xgenplus.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class TestDataReader {
	private static Properties prop;

	public static String getData(String key) {
		try {
			if (prop == null) {
				prop = new Properties();
				FileInputStream fis = new FileInputStream(
						System.getProperty("user.dir") + "/src/main/resources/testdata.properties");
				prop.load(fis);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}
}
