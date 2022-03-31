package com.zalando.testCases;

import com.zalando.steps.PropertiesHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

public class BaseTest {
    protected static Logger logger = LoggerFactory.getLogger("Zalando-logger");
    @BeforeClass
    public static void setUp() {
        logger.error("Entering setup method");
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
