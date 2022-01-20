package com.cydeo.tasks.reviewWeekTasks;

import com.cydeo.pages.AmazonHomepage;
import com.cydeo.tasks.TestBase;
import com.cydeo.utilities.ConfigurationReader;
import com.cydeo.utilities.DriverSetup;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Task1_WithPom extends TestBase {

    @Test
    public void testAddEditCart() throws InterruptedException {

        AmazonHomepage homepage = new AmazonHomepage();
        homepage.findProduct();

        String quantity = ConfigurationReader.getProperties("quantity");
        homepage.selectQty(quantity);

        homepage.navigateToCart();

        String actualQtyText = homepage.getActualQtyText();
        String expectedQtyText = quantity + " item";
        Assert.assertEquals(actualQtyText, expectedQtyText, "Quantity is not true");

        double perItemPrice = homepage.getPricePerItem();
        double subtotal = homepage.getSubtotalPrice();
        double currentQuantity = Double.parseDouble(quantity);
        Assert.assertEquals(subtotal, perItemPrice * currentQuantity);

        int decreaseAmount = Integer.parseInt(ConfigurationReader.getProperties("decreaseAmount"));
        currentQuantity = currentQuantity-decreaseAmount;

        homepage.editQty("" + (int)currentQuantity);//element's class value is seen -1 on html

        expectedQtyText = (int)currentQuantity + " item";
        actualQtyText = homepage.getActualQtyText();
        Assert.assertEquals(actualQtyText, expectedQtyText, "Quantity is not true");

        subtotal = homepage.getSubtotalPrice();
        Assert.assertEquals(subtotal, perItemPrice * currentQuantity);

        DriverSetup.closeDriver();

    }


}
