package com.cydeo.tasks.reviewWeekTasks;

import com.cydeo.pages.AmazonGridwall;
import com.cydeo.pages.AmazonBasePage;
import com.cydeo.pages.AmazonCartPage;
import com.cydeo.pages.AmazonProductPage;
import com.cydeo.tasks.TestBase;
import com.cydeo.utilities.ConfigurationReader;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Task1_AmazonTestExecution extends TestBase { //1.Go to https://www.amazon.com from TestBase

    @Test
    public void testAddEditCart() throws InterruptedException { //checkout TestBase for url
        AmazonBasePage basePage = new AmazonBasePage();
        basePage.searchProduct();//2.Search for "hats for men" (Call from Configuration.properties file)

        AmazonGridwall gridwall = new AmazonGridwall();
        gridwall.findProduct();

        String quantity = ConfigurationReader.getProperties("quantity");
        AmazonProductPage pdp = new AmazonProductPage();
        pdp.selectQty(quantity); //3.Add the first hat appearing to Cart with quantity 2

        basePage.navigateToCart(); //4.Open cart

        AmazonCartPage cartPage = new AmazonCartPage();
        String actualQtyText = cartPage.getActualQtyText();
        String expectedQtyText = quantity + "";
        Assert.assertEquals(actualQtyText, expectedQtyText, "Quantity is not true");//4.Assert that quantity are correct

        double perItemPrice = cartPage.getPricePerItem();
        double subtotal = cartPage.getSubtotalPrice();
        int currentQuantity = Integer.parseInt(quantity);
        Assert.assertEquals(subtotal, perItemPrice * currentQuantity);//4.Assert that the total price are correct

        int decreaseAmount = Integer.parseInt(ConfigurationReader.getProperties("decreaseAmount"));
        currentQuantity = currentQuantity-decreaseAmount;
        cartPage.editQty("" + currentQuantity);//5.Reduce the quantity from 2 to 1 in Cart for the item selected in the step 3

        expectedQtyText = currentQuantity + "";
        actualQtyText = cartPage.getActualQtyText();
        Assert.assertEquals(actualQtyText, expectedQtyText, "Quantity is not true");//6.Assert that the quantity has been correctly changed

        subtotal = cartPage.getSubtotalPrice();
        Assert.assertEquals(subtotal, perItemPrice * currentQuantity);//6.Assert that the total price has been correctly changed

    }

}
