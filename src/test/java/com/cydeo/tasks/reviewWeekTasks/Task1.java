package com.cydeo.tasks.reviewWeekTasks;

import com.cydeo.tasks.TestBase;
import com.cydeo.utilities.ConfigurationReader;
import com.cydeo.utilities.DriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;


public class Task1 extends TestBase {


    @Test
    public void testAddItemToCart() throws InterruptedException {
        String product = ConfigurationReader.getProperties("product_man1");
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys(product);
        WebElement searchBtn = driver.findElement(By.id("nav-search-submit-button"));
        searchBtn.click();

        findProduct();

        int quantity = Integer.parseInt(ConfigurationReader.getProperties("quantity"));
        driver.findElement(By.cssSelector("[class='a-dropdown-label']")).click();
        driver.findElement(By.id("quantity_" + (quantity-1))).click();


        driver.findElement(By.id("add-to-cart-button")).click();
        driver.findElement(By.id("nav-cart")).click();
        driver.navigate().refresh(); //sometimes it gets stuck at the cart page unless refreshing

        String actualQtyText = driver.findElement(By.id("sc-subtotal-label-activecart")).getText().substring(10,16);
        System.out.println("actualQtyText = " + actualQtyText);
        String expectedQtyText = quantity + " item";
        Assert.assertEquals(actualQtyText, expectedQtyText, "Quantity is not true");

        String perItemPriceText = driver.findElement(By.xpath("//p[@class='a-spacing-mini']/span")).getText();
        double perItemPrice = Double.parseDouble(perItemPriceText.substring(1));

        String subtotalText = driver.findElement(By.id("sc-subtotal-amount-activecart")).getText();
        double subtotal = Double.parseDouble(subtotalText.substring(2));

        Assert.assertEquals(subtotal, perItemPrice * quantity);

        int decreaseAmount = Integer.parseInt(ConfigurationReader.getProperties("decreaseAmount"));
        int newQty = quantity-decreaseAmount;

        driver.findElement(By.cssSelector("[class='a-dropdown-label']")).click();//open qty dropdown to decrease qty
        driver.findElement(By.id("quantity_"+(newQty))).click();//decrease qty
        Thread.sleep(2000);

        expectedQtyText = newQty + " item";
        actualQtyText = driver.findElement(By.id("sc-subtotal-label-activecart")).getText().substring(10,16);
        System.out.println("actualQtyText = " + actualQtyText);

        Assert.assertEquals(actualQtyText, expectedQtyText, "Quantity is not true");

        subtotalText = driver.findElement(By.id("sc-subtotal-amount-activecart")).getText();
        System.out.println("subtotalText = " + subtotalText);
        subtotal = Double.parseDouble(subtotalText.substring(2));
        Assert.assertEquals(subtotal, perItemPrice * newQty);

        DriverSetup.closeDriver();


    }

    public void findProduct() throws InterruptedException {
        List<WebElement> products = driver.findElements(By.cssSelector("[data-component-type='s-search-result']"));
        System.out.println("products.size() = " + products.size());

        for(int i = 1; i<products.size(); i++){
            WebElement eachProduct = driver.findElement(By.xpath("(//a[@class='a-link-normal s-no-outline'])[" + i + "]"));
            eachProduct.click();
            WebElement dropdownQty = driver.findElement(By.cssSelector("[class='a-dropdown-label']"));
            if(!dropdownQty.isEnabled()){
                driver.navigate().back();
                Thread.sleep(2000);
            }else{
                break;
            }
        }
    }

}
