package com.cydeo.pages;

import com.cydeo.utilities.DriverSetup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AmazonCartPage {

    WebDriver driver = DriverSetup.getDriver();

    public AmazonCartPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "sc-subtotal-label-activecart")
    public WebElement actualQty;

    @FindBy(xpath = "//p[@class='a-spacing-mini']/span")
    public WebElement perItemPrice;

    @FindBy(id = "sc-subtotal-amount-activecart")
    public WebElement subTotalPrice;

    @FindBy(id = "quantity")
    public WebElement qtyDropdownAtCart;


    public String getActualQtyText() throws InterruptedException {
        Thread.sleep(2000);
        return actualQty.getText().substring(10,11);
    }

    public double getPricePerItem(){
        return Double.parseDouble(perItemPrice.getText().substring(1));
    }

    public double getSubtotalPrice(){
        return Double.parseDouble(subTotalPrice.getText().substring(2));
    }

    public void editQty(String newQuantity){
        Select select = new Select(qtyDropdownAtCart);
        select.selectByVisibleText(newQuantity);
    }
}
