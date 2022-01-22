package com.cydeo.tasks.reviewWeekTasks;

import com.cydeo.pages.AmazonPom;
import com.cydeo.tasks.TestBase;
import com.cydeo.utilities.ConfigurationReader;
import com.cydeo.utilities.DriverSetup;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.*;

public class Task1TestExecution extends TestBase { //1.Go to https://www.amazon.com from TestBase

    @Test
    public void testAddEditCart() throws InterruptedException { //checkout TestBase for url
        AmazonPom amazon = new AmazonPom();
        amazon.findProduct();//2.Search for "hats for men" (Call from Configuration.properties file)

        String quantity = ConfigurationReader.getProperties("quantity");
        amazon.selectQty(quantity); //3.Add the first hat appearing to Cart with quantity 2

        amazon.navigateToCart(); //4.Open cart

        String actualQtyText = amazon.getActualQtyText();
        String expectedQtyText = quantity + " item";
        Assert.assertEquals(actualQtyText, expectedQtyText, "Quantity is not true");//4.Assert that quantity are correct

        double perItemPrice = amazon.getPricePerItem();
        double subtotal = amazon.getSubtotalPrice();
        double currentQuantity = Double.parseDouble(quantity);
        Assert.assertEquals(subtotal, perItemPrice * currentQuantity);//4.Assert that the total price are correct

        int decreaseAmount = Integer.parseInt(ConfigurationReader.getProperties("decreaseAmount"));
        currentQuantity = currentQuantity-decreaseAmount;
        amazon.editQty("" + (int)currentQuantity);//5.Reduce the quantity from 2 to 1 in Cart for the item selected in the step 3

        expectedQtyText = (int)currentQuantity + " item";
        actualQtyText = amazon.getActualQtyText();
        Assert.assertEquals(actualQtyText, expectedQtyText, "Quantity is not true");//6.Assert that the quantity has been correctly changed

        subtotal = amazon.getSubtotalPrice();
        Assert.assertEquals(subtotal, perItemPrice * currentQuantity);//6.Assert that the total price has been correctly changed

    }


}
