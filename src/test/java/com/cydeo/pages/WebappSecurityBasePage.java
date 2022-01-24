package com.cydeo.pages;

import com.cydeo.utilities.DriverSetup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebappSecurityBasePage {
    WebDriver driver = DriverSetup.getDriver();

    public WebappSecurityBasePage(){
        PageFactory.initElements(DriverSetup.getDriver(), this);
    }

    @FindBy(xpath = "//ul[@class='nav float-right']/li[3]//b")
    public WebElement accountMenuDropdown;

}
