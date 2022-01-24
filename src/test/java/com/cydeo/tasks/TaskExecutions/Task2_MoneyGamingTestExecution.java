package com.cydeo.tasks.TaskExecutions;

import com.cydeo.pages.MoneyGamingBasePage;
import com.cydeo.pages.MoneyGamingSubmitPage;
import com.cydeo.tasks.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Task2_MoneyGamingTestExecution extends TestBase {

    @Test
    public void task2() {
        MoneyGamingBasePage gamingBasePage = new MoneyGamingBasePage();
        MoneyGamingSubmitPage submitPage = new MoneyGamingSubmitPage();

        gamingBasePage.clickJoinBtn();
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
