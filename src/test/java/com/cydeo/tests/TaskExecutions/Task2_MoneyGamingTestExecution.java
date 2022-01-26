package com.cydeo.tests.TaskExecutions;

import com.cydeo.pages.MoneyGamingBasePage;
import com.cydeo.pages.MoneyGamingLoginPage;
import com.cydeo.pages.MoneyGamingSubmitPage;
import com.cydeo.tests.TestBase;
import com.cydeo.utilities.ConfigurationReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Task2_MoneyGamingTestExecution extends TestBase {//1.Go to https://moneygaming.qa.gameaccount.com/ from TestBase

    @Test
    public void negativeRegistrationTest() {

        extentLogger = report.createTest("MoneyGaming negative registration test");

        MoneyGamingLoginPage gamingLoginPage = new MoneyGamingLoginPage();
        MoneyGamingSubmitPage submitPage = new MoneyGamingSubmitPage();

        extentLogger.info("Click on join button");
        gamingLoginPage.clickJoinBtn();
        extentLogger.info("Select title:" + ConfigurationReader.getProperties("title"));
        submitPage.selectTitle();
        extentLogger.info("Enter first name:" + ConfigurationReader.getProperties("firstName"));
        submitPage.enterFirstName();
        extentLogger.info("Enter first name:" + ConfigurationReader.getProperties("lastName"));
        submitPage.enterLastName();
        extentLogger.info("Select consent checkbox");
        submitPage.selectConsentCheckbox();
        extentLogger.info("Submit form");
        submitPage.submitForm();
        String actualTxt = submitPage.fieldRequiredTxt();
        String expectedTxt = "This field is required";

        extentLogger.info("Verify \"This field is required is required\" message displayed");
        Assert.assertEquals(actualTxt, expectedTxt, "This field is required is not displayed");
        extentLogger.info("PASS");

    }
}
