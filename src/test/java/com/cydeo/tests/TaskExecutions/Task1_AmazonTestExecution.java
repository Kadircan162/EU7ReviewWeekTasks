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

        AmazonGridwall gridwall = new AmazonGridwall();
        gridwall.findProduct();//2.Search for "hats for men" (Call from Configuration.properties file) and find proper one

        String expectedQtyText = ConfigurationReader.getProperties("quantity");
        AmazonProductPage pdp = new AmazonProductPage();
        pdp.selectQty(expectedQtyText); //3.Add the first hat appearing to Cart with quantity 2
        pdp.addToCart.click();

        AmazonCartPage cartPage = new AmazonCartPage();
        cartPage.navigateToCart(); //4.Open cart

        String actualQtyText = cartPage.getActualQtyText();
        Assert.assertEquals(actualQtyText, expectedQtyText, "Quantity text is not true");//4.Assert that quantity are correct

        double perItemPrice = cartPage.getPricePerItem();
        double subtotal = cartPage.getSubtotalPrice();
        int quantity = Integer.parseInt(expectedQtyText);
        Assert.assertEquals(subtotal, perItemPrice * Integer.parseInt(expectedQtyText), "Subtotal is not correct");//4.Assert that the total price are correct

        int decreaseAmount = Integer.parseInt(ConfigurationReader.getProperties("decreaseAmount"));
        quantity = quantity-decreaseAmount;
        cartPage.editQty("" + quantity);//5.Reduce the quantity from 2 to 1 in Cart for the item selected in the step 3

        actualQtyText = cartPage.getActualQtyText();
        expectedQtyText = quantity + "";
        Assert.assertEquals(actualQtyText, expectedQtyText, "Quantity text is not true");//6.Assert that the quantity has been correctly changed

        subtotal = cartPage.getSubtotalPrice();
        Assert.assertEquals(subtotal, perItemPrice * quantity, "Subtotal is not correct");//6.Assert that the total price has been correctly changed

    }

}
