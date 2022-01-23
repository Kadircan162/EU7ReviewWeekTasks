package com.cydeo.tasks.TaskExecutions;

import com.cydeo.pages.WebappSecurityPom;
import com.cydeo.tasks.TestBase;
import com.cydeo.utilities.ConfigurationReader;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Task3TestExecution extends TestBase{

    WebappSecurityPom appSecurityPom = new WebappSecurityPom();

    @Test
    public void loginPositiveTest(){ //AC1
        appSecurityPom.login();
        String loginPageUrl = ConfigurationReader.getProperties("webappSecurityUrl");
        String actualUrlAfterLogin = driver.getCurrentUrl();
        Assert.assertNotEquals(loginPageUrl, actualUrlAfterLogin, "Could not login"); //AC1 passed
    }

   @Test
    public void verifyAccountSummaryPageDisplayed(){ //AC2
        driver.get(ConfigurationReader.getProperties("url"));
//        if(driver.getPageSource().contains())
//        appSecurityPom.login();
        String actualAccountSummaryUrl = driver.getCurrentUrl();
        String expectedAccountSummaryUrl = ConfigurationReader.getProperties("webappSecurityUrlAfterLogin");
        Assert.assertEquals(actualAccountSummaryUrl, expectedAccountSummaryUrl, "Account Summary page did not open");//AC2 passed
    }
/*
    @Test
    public void loginNegativeTestInvalidUsername(){ //AC3
        String actualAlertMsg = appSecurityPom.loginWithInvalidUsername();
        Assert.assertTrue(appSecurityPom.wrongUsernamePasswordAlert.isDisplayed(), "Logged in with invalid username");
        String expectedAlertMsg = ConfigurationReader.getProperties("webappLoginAlertMsg");
        Assert.assertEquals(expectedAlertMsg, actualAlertMsg, "Alert Msg not correct ");//AC3 and AC4 passed
    }
    @Test
    public void loginNegativeTestInvalidPassword(){ //AC3
        String actualAlertMsg = appSecurityPom.loginWithInvalidPassword();
        Assert.assertTrue(appSecurityPom.wrongUsernamePasswordAlert.isDisplayed(), "Logged in with invalid password");
        String expectedAlertMsg = ConfigurationReader.getProperties("webappLoginAlertMsg");
        Assert.assertEquals(expectedAlertMsg, actualAlertMsg, "Alert Msg not correct ");//AC3 and AC4 passed
    }

    @Test
    public void loginNegativeTestBlankUsername(){ //AC4
        String actualAlertMsg = appSecurityPom.loginWithBlankUsername();
        Assert.assertTrue(appSecurityPom.wrongUsernamePasswordAlert.isDisplayed(), "Logged in with blank username");
        String expectedAlertMsg = ConfigurationReader.getProperties("webappLoginAlertMsg");
        Assert.assertEquals(expectedAlertMsg, actualAlertMsg, "Alert Msg not correct ");//AC3 and AC4 passed
    }

    @Test
    public void loginNegativeTestBlankPassword(){ //AC4
        String actualAlertMsg = appSecurityPom.loginWithBlankPassword();
        Assert.assertTrue(appSecurityPom.wrongUsernamePasswordAlert.isDisplayed(), "Logged in with blank password");
        String expectedAlertMsg = ConfigurationReader.getProperties("webappLoginAlertMsg");
        Assert.assertEquals(expectedAlertMsg, actualAlertMsg, "Alert Msg not correct ");//AC4 passed
    }

    @Test
    public void verifyAccountSummaryTab(){ //AC5
        appSecurityPom.login();
        String actualTitle = driver.getTitle();
        String expectedTitle = ConfigurationReader.getProperties("webappExpectedAccountSummaryTitle");
        Assert.assertEquals(actualTitle, expectedTitle); //AC5 passed
    }

    @Test
    public void verifyAccountSummaryTypes(){ //AC6
        appSecurityPom.login();
        List<String> expectedAccountSummaryTypes = appSecurityPom.getExpectedAccountSummaryTypes();
        List<String> actualAccountSummaryTypes = appSecurityPom.getActualAccountSummaryTypes();
        Assert.assertEquals(expectedAccountSummaryTypes, actualAccountSummaryTypes, "Account types are not matching");//AC6 passed
    }

    @Test
    public void verifyCreditAccountsColumns(){ //AC7
        appSecurityPom.login();
        List<String> expectedCreditAccountColumns = appSecurityPom.getExpectedCreditAccountColumns();
        List<String> actualCreditAccountColumns = appSecurityPom.getActualCreditAccountColumns();
        Assert.assertEquals(expectedCreditAccountColumns, actualCreditAccountColumns, "Creditcar Account columns are not matching");//AC7 passed
    }

    @Test
    public void verifyAccountActivityTitle(){ //AC8
        appSecurityPom.login();
        appSecurityPom.accountActivityTab.click();
        String actualTitle = driver.getTitle();
        String expectedTitle = ConfigurationReader.getProperties("accountActivityTitle");
        Assert.assertEquals(expectedTitle, actualTitle, "Titles are not matching"); //AC8 passed
    }

    @Test
    public void accountActivityDropdownDefaultOptionTest(){ //AC9
        appSecurityPom.login();
        appSecurityPom.accountActivityTab.click();
        String expectedDefaultSelected = ConfigurationReader.getProperties("accountActivityDefaultOption");
        String actualDefaultSelected = appSecurityPom.getActualDefaultOptionForAccountActivityDropdown();
        Assert.assertEquals(expectedDefaultSelected, actualDefaultSelected, "Actual selected option şs not correct");//AC9 passed
    }

    @Test
    public void accountActivityDropdownOptionsListTest(){ //AC10
        appSecurityPom.login();
        appSecurityPom.accountActivityTab.click();
        List<String> expectedAccountActivityDropdownList = appSecurityPom.getExpectedAccountActivityDropdownOptions();
        List<String> actualAccountActivityDropdownList = appSecurityPom.getActualAccountActivityDropdownOptions();
        Assert.assertEquals(expectedAccountActivityDropdownList, actualAccountActivityDropdownList, "Account Activity Dropdown options are not correct");//AC10 passed
    }

    @Test
    public void accountActivityTransactionColumnsTest(){ //AC11
        appSecurityPom.login();
        appSecurityPom.accountActivityTab.click();
        List<String> expectedAccountActivityColumnsList = appSecurityPom.expectedAccountActivityTransactionColumns();
        List<String> actualAccountActivityTransactionColumnList = appSecurityPom.actualAccountActivityTransactionColumns();
        Assert.assertEquals(expectedAccountActivityColumnsList,actualAccountActivityTransactionColumnList, "Account activity type are not correct");
    }

 */









}
