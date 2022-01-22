package com.cydeo.pages;

import com.cydeo.utilities.BrowserUtils;
import com.cydeo.utilities.ConfigurationReader;
import com.cydeo.utilities.DriverSetup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
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

    @FindBy(id = "quantity")
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
            List<String> winTabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(winTabs.get(1));
            driver.get(prodLink);
            if (sideBox.getAttribute("innerHTML").contains("Qty:")) {//iterate until the product has Qty dropdown list
                break;
            }else {
                driver.close();
                driver.switchTo().window(winTabs.get(0));//even the tab gets closed, driver still looks at the new tab(1)
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
        Select select = new Select(qtyDropdownAtCart);
        select.selectByVisibleText(newQuantity);
    }

}
