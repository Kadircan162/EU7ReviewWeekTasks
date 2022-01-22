package com.cydeo.utilities;

import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;
import java.util.List;

public class BrowserUtils {

        public static void openNewWindow(){
            ((JavascriptExecutor)DriverSetup.getDriver()).executeScript("window.open()");
        }

        public static void openProductPage(String prodLink){
            DriverSetup.getDriver().get(prodLink);
        }
}
