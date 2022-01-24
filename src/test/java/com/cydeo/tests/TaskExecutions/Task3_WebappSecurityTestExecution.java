package com.cydeo.tests.TaskExecutions;

import com.cydeo.pages.WebAppAccountActivity;
import com.cydeo.pages.WebAppAccountSummary;
import com.cydeo.pages.WebAppSecurityLoginPage;
import com.cydeo.tests.TestBase;
import com.cydeo.utilities.ConfigurationReader;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.List;

public class Task3_WebappSecurityTestExecution extends TestBase{//1.Go to http://zero.webappsecurity.com/ from TestBase

    WebAppSecurityLoginPage appSecurityLoginPage;
    WebAppAccountSummary accountSummary;
    WebAppAccountActivity accountActivity;
    String username;
    String password;
    String expectedAlertMsg = ConfigurationReader.getProperties("webappLoginAlertMsg");
    String actualAlertMsg;

    @Test
    public void loginPositiveTest(){ //AC1 and AC2
        WebAppSecurityLoginPage appSecurityLoginPage = new WebAppSecurityLoginPage();
        String username = ConfigurationReader.getProperties("wepAppUsername");
        String password = ConfigurationReader.getProperties("webAppPassword");
        appSecurityLoginPage.login(username, password);
        String expectedUrlAfterLogin = ConfigurationReader.getProperties("UrlAfterLogin");
        String actualUrlAfterLogin = driver.getCurrentUrl();
        Assert.assertEquals(actualUrlAfterLogin, expectedUrlAfterLogin,"Could not login/Account Summary page was not open"); //AC1 and AC2 passed

    }

    @Test
    public void loginNegativeTestInvalidUsername(){ //AC3
        appSecurityLoginPage = new WebAppSecurityLoginPage();
        username = ConfigurationReader.getProperties("webAppInvalidUsername");
        password = ConfigurationReader.getProperties("webAppPassword");
        appSecurityLoginPage.login(username, password);
        actualAlertMsg = appSecurityLoginPage.getWebappLoginInvalidAlertMsg();
        Assert.assertTrue(appSecurityLoginPage.wrongUsernamePasswordAlert.isDisplayed(), "Logged in with invalid username");
        Assert.assertEquals(expectedAlertMsg, actualAlertMsg, "Alert Msg not correct ");//AC3 and AC4 passed
    }

    @Test
    public void loginNegativeTestInvalidPassword(){ //AC3
        appSecurityLoginPage = new WebAppSecurityLoginPage();
        username = ConfigurationReader.getProperties("wepAppUsername");
        password = ConfigurationReader.getProperties("webAppInvalidPassword");
        appSecurityLoginPage.login(username, password);
        Assert.assertTrue(appSecurityLoginPage.wrongUsernamePasswordAlert.isDisplayed(), "Logged in with invalid username");
        actualAlertMsg = appSecurityLoginPage.getWebappLoginInvalidAlertMsg();
        Assert.assertEquals(expectedAlertMsg, actualAlertMsg, "Alert Msg not correct ");//AC3 and AC4 passed
    }

    @Test
    public void loginNegativeTestBlankUsername(){ //AC4
        appSecurityLoginPage = new WebAppSecurityLoginPage();
        username = ConfigurationReader.getProperties("webappSecurityBlankUsername");
        password = ConfigurationReader.getProperties("webAppPassword");
        appSecurityLoginPage.login(username, password);
        Assert.assertTrue(appSecurityLoginPage.wrongUsernamePasswordAlert.isDisplayed(), "Logged in with blank username");
        actualAlertMsg = appSecurityLoginPage.getWebappLoginInvalidAlertMsg();
        Assert.assertEquals(expectedAlertMsg, actualAlertMsg, "Alert Msg not correct ");//AC3 and AC4 passed
    }

    @Test
    public void loginNegativeTestBlankPassword(){ //AC4
        appSecurityLoginPage = new WebAppSecurityLoginPage();
        username = ConfigurationReader.getProperties("wepAppUsername");
        password = ConfigurationReader.getProperties("webappSecurityBlankPassword");
        appSecurityLoginPage.login(username, password);
        Assert.assertTrue(appSecurityLoginPage.wrongUsernamePasswordAlert.isDisplayed(), "Logged in with blank username");
        actualAlertMsg = appSecurityLoginPage.getWebappLoginInvalidAlertMsg();
        Assert.assertEquals(expectedAlertMsg, actualAlertMsg, "Alert Msg not correct ");//AC3 and AC4 passed
    }

    @Test
    public void verifyAccountSummaryTab(){ //AC5
        appSecurityLoginPage = new WebAppSecurityLoginPage();
        accountSummary = new WebAppAccountSummary();
        username = ConfigurationReader.getProperties("wepAppUsername");
        password = ConfigurationReader.getProperties("webAppPassword");
        appSecurityLoginPage.login(username, password);
        String actualTitle = accountSummary.getActualTitle();
        String expectedTitle = ConfigurationReader.getProperties("webappExpectedAccountSummaryTitle");
        System.out.println("expectedTitle = " + expectedTitle);
        Assert.assertEquals(actualTitle, expectedTitle); //AC5 passed
    }

    @Test
    public void verifyAccountSummaryTypes(){ //AC6
        appSecurityLoginPage = new WebAppSecurityLoginPage();
        accountSummary = new WebAppAccountSummary();
        username = ConfigurationReader.getProperties("wepAppUsername");
        password = ConfigurationReader.getProperties("webAppPassword");
        appSecurityLoginPage.login(username, password);
        List<String> expectedAccountSummaryTypes = accountSummary.getExpectedAccountSummaryTypes();
        List<String> actualAccountSummaryTypes = accountSummary.getActualAccountSummaryTypes();
        System.out.println(expectedAccountSummaryTypes);
        System.out.println(actualAccountSummaryTypes);
        Assert.assertEquals(expectedAccountSummaryTypes, actualAccountSummaryTypes, "Account types are not matching");//AC6 passed
    }

    @Test
    public void verifyCreditAccountsColumns(){ //AC7
        appSecurityLoginPage = new WebAppSecurityLoginPage();
        accountSummary = new WebAppAccountSummary();
        username = ConfigurationReader.getProperties("wepAppUsername");
        password = ConfigurationReader.getProperties("webAppPassword");
        appSecurityLoginPage.login(username, password);
        List<String> expectedCreditAccountColumns = accountSummary.getExpectedCreditAccountColumns();
        List<String> actualCreditAccountColumns = accountSummary.getActualCreditAccountColumns();
        Assert.assertEquals(expectedCreditAccountColumns, actualCreditAccountColumns, "Credit card Account columns are not matching");//AC7 passed
    }

    @Test
    public void verifyAccountActivityTitle(){ //AC8
        appSecurityLoginPage = new WebAppSecurityLoginPage();
        accountActivity = new WebAppAccountActivity();
        username = ConfigurationReader.getProperties("wepAppUsername");
        password = ConfigurationReader.getProperties("webAppPassword");
        appSecurityLoginPage.login(username, password);
        accountActivity.accountActivityTab.click();
        String actualTitle = accountActivity.getActualTitle();
        String expectedTitle = ConfigurationReader.getProperties("accountActivityTitle");
        Assert.assertEquals(expectedTitle, actualTitle, "Titles are not matching"); //AC8 passed
    }

    @Test
    public void accountActivityDropdownDefaultOptionTest(){ //AC9
        appSecurityLoginPage = new WebAppSecurityLoginPage();
        accountActivity = new WebAppAccountActivity();
        username = ConfigurationReader.getProperties("wepAppUsername");
        password = ConfigurationReader.getProperties("webAppPassword");
        appSecurityLoginPage.login(username, password);
        accountActivity.accountActivityTab.click();
        String expectedDefaultSelected = ConfigurationReader.getProperties("accountActivityDefaultOption");
        String actualDefaultSelected = accountActivity.getActualDefaultOptionForAccountActivityDropdown();
        Assert.assertEquals(expectedDefaultSelected, actualDefaultSelected, "Actual selected option şs not correct");//AC9 passed
    }

    @Test
    public void accountActivityDropdownOptionsListTest(){ //AC10
        appSecurityLoginPage = new WebAppSecurityLoginPage();
        accountActivity = new WebAppAccountActivity();
        username = ConfigurationReader.getProperties("wepAppUsername");
        password = ConfigurationReader.getProperties("webAppPassword");
        appSecurityLoginPage.login(username, password);
        accountActivity.accountActivityTab.click();
        List<String> expectedAccountActivityDropdownList = accountActivity.getExpectedAccountActivityDropdownOptions();
        List<String> actualAccountActivityDropdownList = accountActivity.getActualAccountActivityDropdownOptions();
        Assert.assertEquals(expectedAccountActivityDropdownList, actualAccountActivityDropdownList, "Account Activity Dropdown options are not correct");//AC10 passed
    }

    @Test
    public void accountActivityTransactionColumnsTest(){ //AC11
        appSecurityLoginPage = new WebAppSecurityLoginPage();
        accountActivity = new WebAppAccountActivity();
        username = ConfigurationReader.getProperties("wepAppUsername");
        password = ConfigurationReader.getProperties("webAppPassword");
        appSecurityLoginPage.login(username, password);
        accountActivity.accountActivityTab.click();
        List<String> expectedAccountActivityColumnsList = accountActivity.expectedAccountActivityTransactionColumns();
        List<String> actualAccountActivityTransactionColumnList = accountActivity.actualAccountActivityTransactionColumns();
        Assert.assertEquals(expectedAccountActivityColumnsList,actualAccountActivityTransactionColumnList, "Account activity type are not correct");
    }

}
