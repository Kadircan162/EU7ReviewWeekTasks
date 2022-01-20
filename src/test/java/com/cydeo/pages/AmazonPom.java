package com.cydeo.pages;

import com.cydeo.utilities.BrowserUtils;
import com.cydeo.utilities.ConfigurationReader;
import com.cydeo.utilities.DriverSetup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AmazonPom {

    public AmazonPom(){
        PageFactory.initElements(DriverSetup.getDriver(), this);
    }

    @FindBy(id = "twotabsearchtextbox")
    public WebElement searchBox;

    @FindBy(id = "nav-search-submit-button")
    public WebElement findBtn;

    @FindBy(css = "[class='a-link-normal s-no-outline']")
    public List<WebElement> prodList;

    @FindBy(css = "#quantity")
    public WebElement qtyDropdownAtPdp;

    @FindBy(id = "add-to-cart-button")
    public WebElement addToCart;

    @FindBy(id = "nav-cart")
    public WebElement goToCart;

    @FindBy(id = "sc-subtotal-label-activecart")
    public WebElement actualQty;

    @FindBy(xpath = "//p[@class='a-spacing-mini']/span")
    public WebElement perItemPrice;

    @FindBy(id = "sc-subtotal-amount-activecart")
    public WebElement subTotalPrice;

    @FindBy(css = "[name='quantity']")
    public WebElement qtyDropdownAtCart;

    @FindBy(css = "[class='a-box-inner']")
    public WebElement sideBox;

    WebDriver driver = DriverSetup.getDriver();

    public void findProduct(){
        String product = ConfigurationReader.getProperties("product_man1");
        searchBox.sendKeys(product);
        findBtn.click();

        for (WebElement prod : prodList) {
            String prodLink =  prod.getAttribute("href");
            BrowserUtils.openNewWindow();
            BrowserUtils.switchToWindow(1);
            BrowserUtils.openProductPage(prodLink);
            if (sideBox.getAttribute("innerHTML").contains("Qty:")) {//iterate until the product has Qty dropdown list or not
                break;
            }else {
                driver.close();
                BrowserUtils.switchToWindow(0);//even the tab gets closed, driver still looks at the new tab(1)
            }
        }

    }

    public void selectQty(String quantity){
        Select select = new Select(qtyDropdownAtPdp);
        select.selectByVisibleText(quantity);
        addToCart.click();
    }

    public void navigateToCart(){
        goToCart.click();
        driver.navigate().refresh(); //sometimes it gets stuck at the cart page unless refreshing
    }

    public String getActualQtyText() throws InterruptedException {
        Thread.sleep(2000);
        return actualQty.getText().substring(10,16);
    }

    public double getPricePerItem(){
       return Double.parseDouble(perItemPrice.getText().substring(1));
    }

    public double getSubtotalPrice(){
        return Double.parseDouble(subTotalPrice.getText().substring(2));
    }

    public void editQty(String newQuantity){
        System.out.println("newQuantity = " + newQuantity);
        Select select2 = new Select(qtyDropdownAtCart);
        select2.selectByVisibleText(newQuantity);
    }

}
