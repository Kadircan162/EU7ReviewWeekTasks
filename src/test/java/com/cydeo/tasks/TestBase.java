package com.cydeo.tasks;

import com.cydeo.utilities.ConfigurationReader;
import com.cydeo.utilities.DriverSetup;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    protected WebDriver driver;

    @BeforeMethod
    public void startUp(){
        driver = DriverSetup.getDriver();
        driver.get(ConfigurationReader.getProperties("urlMoneyGaming"));//1. Go to url in configuration.properties
    }
    @AfterMethod
    public void burnDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();

    }

}
