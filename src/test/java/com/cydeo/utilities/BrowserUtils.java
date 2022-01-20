package com.cydeo.utilities;

import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;
import java.util.List;

public class BrowserUtils {

        public static void openNewWindow(){
            ((JavascriptExecutor)DriverSetup.getDriver()).executeScript("window.open()");
        }

        public static void switchToWindow(int tabNumber){
            List<String> tabs = new ArrayList<>(DriverSetup.getDriver().getWindowHandles());
            DriverSetup.getDriver().switchTo().window(tabs.get(1));
        }

        public static void openProductPage(String prodLink){
            DriverSetup.getDriver().get(prodLink);
        }
}
