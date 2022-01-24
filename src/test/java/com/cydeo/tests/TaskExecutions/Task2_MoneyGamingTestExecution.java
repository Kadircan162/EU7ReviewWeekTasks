package com.cydeo.tests.TaskExecutions;

import com.cydeo.pages.MoneyGamingBasePage;
import com.cydeo.pages.MoneyGamingLoginPage;
import com.cydeo.pages.MoneyGamingSubmitPage;
import com.cydeo.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Task2_MoneyGamingTestExecution extends TestBase {//1.Go to https://moneygaming.qa.gameaccount.com/ from TestBase

    @Test
    public void task2() {
        MoneyGamingLoginPage gamingLoginPage = new MoneyGamingLoginPage();
        MoneyGamingSubmitPage submitPage = new MoneyGamingSubmitPage();

        gamingLoginPage.clickJoinBtn();
        submitPage.selectTitle();
        submitPage.enterFirstName();
        submitPage.enterLastName();
        submitPage.selectConsentCheckbox();
        submitPage.submitForm();
        String actualTxt = submitPage.fieldRequiredTxt();
        String expectedTxt = "This field is required";

        Assert.assertEquals(actualTxt, expectedTxt, "This field is required is not displayed");


    }
}
