package com.zalando.testCases;

import com.zalando.steps.PropertiesHandler;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

public class BaseTest {
    
    @BeforeClass
    public static void setUp() {
        PropertiesHandler.getProperties();
    }

    @AfterClass
    public void tearDown(){
        try {
            Runtime.getRuntime().exec("allure serve allure-results\n");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
