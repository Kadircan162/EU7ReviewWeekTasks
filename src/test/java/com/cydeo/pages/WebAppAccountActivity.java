package com.cydeo.pages;

import com.cydeo.utilities.ConfigurationReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.List;

public class WebAppAccountActivity extends WebappSecurityBasePage{

    @FindBy(xpath = "//a[.='Account Activity']")
    public WebElement accountActivityTab;

    @FindBy(id = "aa_accountId")
    public WebElement accountActivityDropdown;

    @FindBy(xpath = "//*[@id='aa_accountId']/option")
    public List<WebElement> accountActivityDropdownOptions;

    @FindBy(xpath = "//*[@id='all_transactions_for_account']//th")
    public List<WebElement> transactionsColumns;

    public String getActualTitle(){
        return driver.getTitle();
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
