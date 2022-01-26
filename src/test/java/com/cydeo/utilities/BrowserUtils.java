package com.cydeo.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BrowserUtils {

    /*
     * takes screenshot
     * @param name
     * take a name of a test and returns a path to screenshot takes
     */
    public static String getScreenshot(String name) throws IOException {
        // name the screenshot with the current date time to avoid duplicate name
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        // TakesScreenshot ---> interface from selenium which takes screenshots
        TakesScreenshot ts = (TakesScreenshot) DriverSetup.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        // full path to the screenshot location
        String target = System.getProperty("user.dir") + "/test-output/Screenshots/" + name + date + ".png";
        File finalDestination = new File(target);
        // save the screenshot to the path given
        FileUtils.copyFile(source, finalDestination);
        return target;//returns screenshot path
    }

    public static void openNewWindow(){
        ((JavascriptExecutor)DriverSetup.getDriver()).executeScript("window.open()");
    }

    public static List<String> getExpectedValues(String key, int n){
        List<String> expectedDOptions = new ArrayList<>();
        for(int i=1; i<=n; i++){
            expectedDOptions.add(ConfigurationReader.getProperties(key + i));//getting key values from configuration.properties
        }
        return expectedDOptions;
    }

    public static List<String> getTextOfList(List<WebElement> eList){
        List<String> actualDropdownOptions = new ArrayList<>();
        for (WebElement each : eList) {
            actualDropdownOptions.add(each.getText());
        }
        return actualDropdownOptions;
    }

}
