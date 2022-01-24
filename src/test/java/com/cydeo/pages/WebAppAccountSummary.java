package com.cydeo.pages;

import com.cydeo.utilities.BrowserUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class WebAppAccountSummary extends WebappSecurityBasePage{

    @FindBy(xpath = "//*[@class='board'][3]//th")
    public List<WebElement> creditAccountsColumns;

    @FindBy(xpath = "//h2")
    public List<WebElement> accountSummaryAccountTypes;

    public String getActualTitle(){
        return driver.getTitle();
    }

    public List<String> getExpectedAccountSummaryValues(String key, int n){
        return BrowserUtils.getExpectedValues(key, n);
    }

    public List<String> getActualAccountSummaryTypes(){
        return BrowserUtils.getTextOfList(accountSummaryAccountTypes);
    }

    public List<String> getActualCreditAccountColumns(){
        return BrowserUtils.getTextOfList(creditAccountsColumns);
    }
}
