package com.cydeo.tests.TaskExecutions;

import com.cydeo.pages.AmazonGridwall;
import com.cydeo.pages.AmazonCartPage;
import com.cydeo.pages.AmazonProductPage;
import com.cydeo.tests.TestBase;
import com.cydeo.utilities.ConfigurationReader;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Task1_AmazonTestExecution extends TestBase { //1.Go to https://www.amazon.com from TestBase

    @Test
    public void testAddEditCart() throws InterruptedException { //checkout TestBase for url

        extentLogger = report.createTest("Amazon cart - item quantity change test");

        AmazonGridwall gridwall = new AmazonGridwall();

        extentLogger.info("Search for: " +  ConfigurationReader.getProperties("product_man1"));
        extentLogger.info("Open a hat product with Qty dropdown");
        gridwall.findProduct();//2.Search for "hats for men" (Call from Configuration.properties file) and find proper one

        String expectedQtyText = ConfigurationReader.getProperties("quantity");
        AmazonProductPage pdp = new AmazonProductPage();

        extentLogger.info("Change product quantity to: " + expectedQtyText);
        extentLogger.info("Add the hat product to Cart");
        pdp.selectQty(expectedQtyText); //3.Add the first hat appearing to Cart with quantity 2
        pdp.addToCart.click();

        AmazonCartPage cartPage = new AmazonCartPage();

        extentLogger.info("Open the cart");
        cartPage.navigateToCart(); //4.Open cart

        String actualQtyText = cartPage.getActualQtyText();
        extentLogger.info("Verify the item Quantity text is true");
        Assert.assertEquals(actualQtyText, expectedQtyText, "Quantity text is not true");//4.Assert that quantity are correct

        double perItemPrice = cartPage.getPricePerItem();
        double subtotal = cartPage.getSubtotalPrice();
        int quantity = Integer.parseInt(expectedQtyText);

        extentLogger.info("Verify subtotal of the item price is calculated well");
        Assert.assertEquals(subtotal, perItemPrice * Integer.parseInt(expectedQtyText), "Subtotal is not correct");//4.Assert that the total price are correct

        int decreaseAmount = Integer.parseInt(ConfigurationReader.getProperties("decreaseAmount"));
        quantity = quantity-decreaseAmount;
        extentLogger.info("Change item quantity to: " + quantity);
        cartPage.editQty("" + quantity);//5.Reduce the quantity from 2 to 1 in Cart for the item selected in the step 3

        actualQtyText = cartPage.getActualQtyText();
        expectedQtyText = quantity + "";

        extentLogger.info("Verify quantity text was changed accordingly");
        Assert.assertEquals(actualQtyText, expectedQtyText, "Quantity text is not true");//6.Assert that the quantity has been correctly changed

        subtotal = cartPage.getSubtotalPrice();
        extentLogger.info("Verify subtotal of the item price is calculated well after changing the quantity");
        Assert.assertEquals(subtotal, perItemPrice * quantity, "Subtotal is not correct");//6.Assert that the total price has been correctly changed
        extentLogger.info("PASS");
    }

}
