package com.wang.spring.dao.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesConfig {

    private static Properties properties;

    static {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("server.properties");

        properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            System.out.println("file not found");
            e.printStackTrace();
        }
    }

    public static Properties getProperties() {
        return properties;
    }

}
