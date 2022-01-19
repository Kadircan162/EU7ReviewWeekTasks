package com.cydeo.tasks.reviewWeekTasks;

import com.cydeo.pages.AmazonHomepage;
import com.cydeo.tasks.TestBase;
import com.cydeo.utilities.ConfigurationReader;
import com.cydeo.utilities.DriverSetup;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


public class Task1WithPom extends TestBase {


    @Test
    public void testAddItemToCart() throws InterruptedException {

        AmazonHomepage homepage = new AmazonHomepage();

        String product = ConfigurationReader.getProperties("product_man1");
        homepage.findProduct(product);

        int quantity = Integer.parseInt(ConfigurationReader.getProperties("quantity"));
        int decreaseAmount = Integer.parseInt(ConfigurationReader.getProperties("decreaseAmount"));
        homepage.editQty(quantity);

        homepage.navigateToCart();

        String actualQtyText = homepage.getActualQtyText();
        String expectedQtyText = quantity + " item";
        Assert.assertEquals(actualQtyText, expectedQtyText, "Quantity is not true");

        double perItemPrice = homepage.getPerItemPrice();
        double subtotal = homepage.getSubtotalPrice();
        Assert.assertEquals(subtotal, perItemPrice * quantity);

        quantity = quantity-decreaseAmount;
        homepage.editQty(quantity+1);//element's class value is seen -1 on html
        Thread.sleep(2000);

        expectedQtyText = quantity + " item";
        actualQtyText = homepage.getActualQtyText();
        Assert.assertEquals(actualQtyText, expectedQtyText, "Quantity is not true");

        subtotal = homepage.getSubtotalPrice();
        Assert.assertEquals(subtotal, perItemPrice * quantity);

        DriverSetup.closeDriver();


    }



}
