package com.cydeo.tasks.reviewWeekTasks;

import com.cydeo.pages.MoneyGamingPOM;
import com.cydeo.tasks.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Task2TestExecution extends TestBase {

    @Test
    public void task2() throws InterruptedException {
        MoneyGamingPOM moneygamingPOM = new MoneyGamingPOM();

        moneygamingPOM.clickJoinBtn();
        moneygamingPOM.selectTitle();
        moneygamingPOM.enterFirstName();
        moneygamingPOM.enterLastName();
        moneygamingPOM.selectConsentCheckbox();
        moneygamingPOM.submitForm();
        String actualTxt = moneygamingPOM.fieldRequiredTxt();
        String expectedTxt = "This field is required";

        Assert.assertEquals(actualTxt, expectedTxt, "This field is required is not displayed");


    }
}
