package com.cydeo.pages;

import com.cydeo.utilities.ConfigurationReader;
import com.cydeo.utilities.DriverSetup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AmazonBasePage {

    public AmazonBasePage(){
        PageFactory.initElements(DriverSetup.getDriver(), this);
    }

    @FindBy(id = "twotabsearchtextbox")
    public WebElement searchBox;

    @FindBy(id = "nav-search-submit-button")
    public WebElement searchBtn;

    @FindBy(id = "nav-cart")
    public WebElement goToCart;

    WebDriver driver = DriverSetup.getDriver();

    public void searchProduct(){
        String product = ConfigurationReader.getProperties("product_man1");
        searchBox.sendKeys(product);
        searchBtn.click();
    }

    public void navigateToCart(){
        goToCart.click();
    }

}
