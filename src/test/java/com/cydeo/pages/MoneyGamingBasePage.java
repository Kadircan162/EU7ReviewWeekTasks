package com.cydeo.pages;

import com.cydeo.utilities.DriverSetup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MoneyGamingBasePage {
    WebDriver driver = DriverSetup.getDriver();

    public MoneyGamingBasePage(){
        PageFactory.initElements(driver, this);
    }


}
