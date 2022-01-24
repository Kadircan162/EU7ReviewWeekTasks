package com.cydeo.pages;

import com.cydeo.utilities.ConfigurationReader;
import com.cydeo.utilities.DriverSetup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;

public class WebAppAccountSummary {
    WebDriver driver = DriverSetup.getDriver();

    public WebAppAccountSummary(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@class='board'][3]//th")
    public List<WebElement> creditAccountsColumns;

    @FindBy(css = "[class='board-header']")
    public List<WebElement> accountSummaryAccountTypes;

    public String getActualTitle(){
        return driver.getTitle();
    }

    public List<String> getExpectedAccountSummaryTypes(){//AC6
        List<String> expectedDOptions = new ArrayList<>();
        for(int i=1; i<=4; i++){
            expectedDOptions.add(ConfigurationReader.getProperties("accountSummaryType" + i));
        }
        return expectedDOptions;
    }

    public List<String> getActualAccountSummaryTypes(){
        List<String> actualDropdownOptions = new ArrayList<>();
        for (WebElement each : accountSummaryAccountTypes) {
            actualDropdownOptions.add(each.getText());
        }
        return actualDropdownOptions;
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
}
