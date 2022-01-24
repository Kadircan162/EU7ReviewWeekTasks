package com.cydeo.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class BrowserUtils {

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
