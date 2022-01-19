package com.cydeo.pages;

import com.cydeo.utilities.ConfigurationReader;
import com.cydeo.utilities.DriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AmazonHomepage {

    public AmazonHomepage(){
        PageFactory.initElements(DriverSetup.getDriver(), this);
    }

    @FindBy(id = "twotabsearchtextbox")
    public WebElement searchBox;

    @FindBy(id = "nav-search-submit-button")
    public WebElement findBtn;

    @FindBy(css = "[class='a-dropdown-label']")
    public WebElement qtyDropdown;

    @FindBy(id = "sc-subtotal-label-activecart")
    public WebElement actualQty;

    @FindBy(xpath = "//p[@class='a-spacing-mini']/span")
    public WebElement perItemPrice;

    @FindBy(id = "sc-subtotal-amount-activecart")
    public WebElement subTotalPrice;




    WebDriver driver = DriverSetup.getDriver();

    public void findProduct(String product) throws InterruptedException {

        searchBox.sendKeys(product);
        findBtn.click();

        List<WebElement> products = driver.findElements(By.cssSelector("[data-component-type='s-search-result']"));
        System.out.println("products.size() = " + products.size());
        System.out.println("products = " + products);

        for (WebElement webElement : products) {
            webElement.click();
            if (!qtyDropdown.isEnabled()) {
                driver.navigate().back();
                Thread.sleep(2000);
            } else {
                break;
            }
        }
    }

    public void editQty(int quantity){
        qtyDropdown.click();//open qty dropdown to decrease qty
        driver.findElement(By.id("quantity_" + (quantity-1))).click();//define or edit qty
    }

    public void navigateToCart(){
        driver.findElement(By.id("add-to-cart-button")).click();
        driver.findElement(By.id("nav-cart")).click();
        driver.navigate().refresh(); //sometimes it gets stuck at the cart page unless refreshing
    }

    public String getActualQtyText(){
        return actualQty.getText().substring(10,16);
    }

    public double getPerItemPrice(){
       return Double.parseDouble(perItemPrice.getText().substring(1));
    }

    public double getSubtotalPrice(){
        return Double.parseDouble(subTotalPrice.getText().substring(2));
    }



}
