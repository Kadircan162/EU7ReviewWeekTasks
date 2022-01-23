package com.cydeo.pages;

import com.cydeo.utilities.ConfigurationReader;
import com.cydeo.utilities.DriverSetup;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.List;

public class WebappSecurityPom {

    public WebappSecurityPom(){
        PageFactory.initElements(DriverSetup.getDriver(), this);
    }

    @FindBy(id = "signin_button")
    public WebElement signInBtn;

    @FindBy(id = "user_login")
    public WebElement usernameInputBox;

    @FindBy(id = "user_password")
    public WebElement passwordInputBox;

    @FindBy(css = "[class='btn btn-primary']")
    public WebElement signInSubmitBtn;

    @FindBy(id = "details-button")
    public WebElement advancedBtn;

    @FindBy(id = "proceed-link")
    public WebElement proceedBtn;

    @FindBy(xpath = "//ul[@class='nav float-right']/li[3]//b")
    public WebElement accountMenuDropdown;

    @FindBy(id = "logout_link")
    public WebElement logoutBtn;

    @FindBy(css = "[class='board-header']")
    public List<WebElement> accountSummaryAccountTypes;

    @FindBy(xpath = "//*[@class='alert alert-error']")
    public WebElement wrongUsernamePasswordAlert;

    @FindBy(xpath = "//*[@class='board'][3]//th")
    public List<WebElement> creditAccountsColumns;

    @FindBy(xpath = "//a[.='Account Activity']")
    public WebElement accountActivityTab;

    @FindBy(id = "aa_accountId")
    public WebElement accountActivityDropdown;

    @FindBy(xpath = "//*[@id='aa_accountId']/option")
    public List<WebElement> accountActivityDropdownOptions;

    @FindBy(xpath = "//*[@id='all_transactions_for_account']//th")
    public List<WebElement> transactionsColumns;

    public void login(){
        signInBtn.click();
        String username = ConfigurationReader.getProperties("webappSecurityUsername");
        String password = ConfigurationReader.getProperties("webappSecurityPassword");
        usernameInputBox.sendKeys(username);
        passwordInputBox.sendKeys(password);
        signInSubmitBtn.click();
        advancedBtn.click();
        proceedBtn.click();
    }
    public void loginSuccessive(){
        signInBtn.click();
        String username = ConfigurationReader.getProperties("webappSecurityUsername");
        String password = ConfigurationReader.getProperties("webappSecurityPassword");
        usernameInputBox.sendKeys(username);
        passwordInputBox.sendKeys(password);
        signInSubmitBtn.click();
    }

    public void logout(){
        accountMenuDropdown.click();
        logoutBtn.click();
    }

    public String loginWithInvalidUsername(){
        signInBtn.click();
        String username = ConfigurationReader.getProperties("webappSecurityInvalidUsername");
        String password = ConfigurationReader.getProperties("webappSecurityPassword");
        usernameInputBox.sendKeys(username);
        passwordInputBox.sendKeys(password);
        signInSubmitBtn.click();
        return wrongUsernamePasswordAlert.getText();
    }
    public String loginWithInvalidPassword(){
        signInBtn.click();
        String username = ConfigurationReader.getProperties("webappSecurityUsername");
        String password = ConfigurationReader.getProperties("webappSecurityInvalidPassword");
        usernameInputBox.sendKeys(username);
        passwordInputBox.sendKeys(password);
        signInSubmitBtn.click();
        return wrongUsernamePasswordAlert.getText();
    }

    public String loginWithBlankUsername(){
        signInBtn.click();
        String username = ConfigurationReader.getProperties("webappSecurityBlankUsername");
        String password = ConfigurationReader.getProperties("webappSecurityPassword");
        usernameInputBox.sendKeys(username);
        passwordInputBox.sendKeys(password);
        signInSubmitBtn.click();
        return wrongUsernamePasswordAlert.getText();
    }

    public String loginWithBlankPassword(){
        signInBtn.click();
        String username = ConfigurationReader.getProperties("webappSecurityUsername");
        String password = ConfigurationReader.getProperties("webappSecurityBlankPassword");
        usernameInputBox.sendKeys(username);
        passwordInputBox.sendKeys(password);
        signInSubmitBtn.click();
        return wrongUsernamePasswordAlert.getText();
    }


    public List<String> getExpectedAccountSummaryTypes(){//AC6
        List<String> expectedDOptions = new ArrayList<>();
        for(int i=1; i<=4; i++){
            expectedDOptions.add(ConfigurationReader.getProperties("accountSummaryType" + i));
        }
        return expectedDOptions;
    }

    public List<String> getActualAccountSummaryTypes(){
        List<String> actualDropOptions = new ArrayList<>();
        for (WebElement each : accountSummaryAccountTypes) {
            actualDropOptions.add(each.getText());
        }
        return actualDropOptions;
    }

    public List<String> getExpectedCreditAccountColumns(){
        List<String> expectedCreditAccountColumns = new ArrayList<>();
        for (int i=1; i<=3; i++){
            expectedCreditAccountColumns.add(ConfigurationReader.getProperties("creditAccountsColumn" + i));
        }
        return expectedCreditAccountColumns;
    }

    public List<String> getActualCreditAccountColumns(){
        List<String> creditAccountColumnList = new ArrayList<>();
        for (WebElement each : creditAccountsColumns) {
            creditAccountColumnList.add(each.getText());
        }
        return creditAccountColumnList;
    }

    public String getActualDefaultOptionForAccountActivityDropdown(){
        Select select = new Select(accountActivityDropdown);
        return select.getFirstSelectedOption().getText();
    }

    public List<String> getExpectedAccountActivityDropdownOptions(){
        List<String> expectedDropdownList = new ArrayList<>();
        for(int i=1; i<=5; i++){
            expectedDropdownList.add(ConfigurationReader.getProperties("accountActivityDropdownOption"+ i));
        }
        return expectedDropdownList;
    }

    public List<String> getActualAccountActivityDropdownOptions(){
        List<String> actualDropdownList = new ArrayList<>();
        for (WebElement each : accountActivityDropdownOptions) {
            actualDropdownList.add(each.getText());
        }
        actualDropdownList.remove(0);
        return actualDropdownList;
    }

    public List<String> expectedAccountActivityTransactionColumns(){
        List<String> expectedTransactionColumnsList = new ArrayList<>();
        for(int i=1; i<=4; i++){
            expectedTransactionColumnsList.add(ConfigurationReader.getProperties("transactionsColumn" + i));
        }
        return expectedTransactionColumnsList;
    }

    public List<String> actualAccountActivityTransactionColumns(){
        List<String> actualTransactionColumnsList = new ArrayList<>();
        for (WebElement each : transactionsColumns) {
            actualTransactionColumnsList.add(each.getText());
        }
        return actualTransactionColumnsList;
    }






}
