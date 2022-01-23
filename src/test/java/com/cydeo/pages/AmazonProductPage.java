package com.cydeo.pages;

import com.cydeo.utilities.DriverSetup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AmazonProductPage {

    WebDriver driver = DriverSetup.getDriver();

    public AmazonProductPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#quantity")
    public WebElement qtyDropdownAtPdp;

    @FindBy(id = "add-to-cart-button")
    public WebElement addToCart;


    public boolean isQtyDropdownDisplayed(){
        return qtyDropdownAtPdp.isDisplayed();
    }

    public void selectQty(String quantity){
        Select select = new Select(qtyDropdownAtPdp);
        select.selectByVisibleText(quantity);
        addToCart.click();
    }
}
