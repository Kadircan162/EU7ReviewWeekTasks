package com.cydeo.pages;

import com.cydeo.utilities.DriverSetup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebAppSecurityLoginPage {
    WebDriver driver = DriverSetup.getDriver();

    public WebAppSecurityLoginPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "signin_button")
    public WebElement signInBtn;

    @FindBy(id = "user_login")
    public WebElement usernameInputBox;

    @FindBy(id = "user_password")
    public WebElement passwordInputBox;

    @FindBy(css = "[class='btn btn-primary']")
    public WebElement signInSubmitBtn;

    @FindBy(xpath = "//*[@class='alert alert-error']")
    public WebElement wrongUsernamePasswordAlert;

    @FindBy(id = "details-button")
    public WebElement advancedBtn;

    @FindBy(id = "proceed-link")
    public WebElement proceedBtn;

    public void login(String username, String password){
        try {
            signInBtn.click();
            usernameInputBox.sendKeys(username);
            passwordInputBox.sendKeys(password);
            signInSubmitBtn.click();
            advancedBtn.click();
            proceedBtn.click();
        }catch (Exception e){
            System.out.println("advancedBtn is not visible for negative test");
        }
    }

    public String getWebappLoginInvalidAlertMsg(){
        return wrongUsernamePasswordAlert.getText();
    }
}
