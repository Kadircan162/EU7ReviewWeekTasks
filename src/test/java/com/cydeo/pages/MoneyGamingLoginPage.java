package com.cydeo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MoneyGamingLoginPage extends MoneyGamingBasePage{

    @FindBy(xpath = "//a[.='Join Now!']")
    public WebElement joinNowBtn;

    public void clickJoinBtn(){
        joinNowBtn.click();
    }
}
