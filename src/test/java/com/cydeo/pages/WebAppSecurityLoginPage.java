package com.cydeo.pages;

import com.cydeo.utilities.ConfigurationReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WebAppSecurityLoginPage extends WebappSecurityBasePage{

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

    String username;
    String password;

    private void login(String username, String password){
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

    public void loginPositiveTest(){ //AC1 and AC2
        String username = ConfigurationReader.getProperties("wepAppUsername");
        String password = ConfigurationReader.getProperties("webAppPassword");
        login(username, password);
    }

    public String getWebappLoginInvalidAlertMsg(){
        return wrongUsernamePasswordAlert.getText();
    }

    public void loginNegativeTestInvalidUsername(){ //AC3
        username = ConfigurationReader.getProperties("webAppInvalidUsername");
        password = ConfigurationReader.getProperties("webAppPassword");
        login(username, password);
    }

    public void loginNegativeTestInvalidPassword(){ //AC3
        username = ConfigurationReader.getProperties("wepAppUsername");
        password = ConfigurationReader.getProperties("webAppInvalidPassword");
        login(username, password);
    }

    public void loginNegativeTestBlankUsername(){ //AC4
        username = ConfigurationReader.getProperties("webappSecurityBlankUsername");
        password = ConfigurationReader.getProperties("webAppPassword");
        login(username, password);
    }

    public void loginNegativeTestBlankPassword(){ //AC4
        username = ConfigurationReader.getProperties("wepAppUsername");
        password = ConfigurationReader.getProperties("webappSecurityBlankPassword");
        login(username, password);
    }



}
