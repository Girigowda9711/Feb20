package com.orangehrm.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	private static Properties prop;

    // Static block to initialize properties
    static {
        try {
            FileInputStream file = new FileInputStream("./src/test/resources/config.properties");
            prop = new Properties();
            prop.load(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties file");
        }
    }

    // Method to get property values
    public static String getProperty(String key) {
        return prop.getProperty(key);
    }


}
