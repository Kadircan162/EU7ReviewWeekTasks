package com.cydeo.tasks.TaskExecutions;

import com.cydeo.pages.MoneyGamingPOM;
import com.cydeo.tasks.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Task2TestExecution extends TestBase {

    @Test
    public void task2() {
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
