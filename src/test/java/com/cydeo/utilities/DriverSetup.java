package com.cydeo.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverSetup {

    private DriverSetup(){}

    private static WebDriver driver;

    public static WebDriver getDriver(){

        if(driver == null){
            String browser = ConfigurationReader.getProperties("browser");

            switch (browser.toLowerCase()){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "chrome-headless":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                default:
                    driver = null;
                    break;
            }

        }
        driver.manage().window().maximize();
        return driver;

    }

    public static void closeDriver(){
        if(driver != null){
            driver.quit();
            driver = null;
        }
    }
}
