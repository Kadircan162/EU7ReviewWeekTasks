package com.cydeo.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationReader {

    private static Properties properties;

    static {
        String properties_path = "configuration.properties";
        try {
            FileInputStream inputStream = new FileInputStream(properties_path);//throws "FileNotFound Exception"
            properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String getProperties(String keyName){
        return properties.getProperty(keyName);
    }
}
