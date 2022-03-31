package com.zalando.steps;

import java.io.*;
import java.util.Properties;

public class PropertiesHandler {

    public static Properties properties = new Properties();
    private static String path = System.getProperty("user.dir");

    public static void getProperties(){

        try{
            InputStream inputStream = new FileInputStream(path + "/src/test/resources/application-test.properties");
            properties.load(inputStream);
        } catch (IOException e){
            System.out.println(e.getCause());
            e.printStackTrace();
        }
    }

    public static void setProperties(String key, String value, String comment){
        try{
            OutputStream outputStream = new FileOutputStream(path + "src/test/resources/application-test.properties");
            properties.store(outputStream, comment);
        } catch (IOException e){
            System.out.println(e.getCause());
            e.printStackTrace();
        }
    }

}
