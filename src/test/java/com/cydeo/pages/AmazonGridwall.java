package com.cydeo.pages;

import com.cydeo.utilities.BrowserUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.List;

public class AmazonGridwall extends AmazonBasePage{

    @FindBy(css = "[class='a-link-normal s-no-outline']")
    public List<WebElement> prodList;

    public void findProduct(){
        searchProduct();
        AmazonProductPage pdp = new AmazonProductPage();

        for (WebElement prod : prodList) {
            String prodLink =  prod.getAttribute("href");
            BrowserUtils.openNewWindow();
            List<String> winTabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(winTabs.get(1)).get(prodLink);
            try {
                if(pdp.isQtyDropdownDisplayed()){
                    break;
                }
            }catch (Exception e){
                driver.close();
                driver.switchTo().window(winTabs.get(0));//even the tab gets closed, driver still looks at the new tab(1)
            }
        }
    }


}
