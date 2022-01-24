package com.cydeo.pages;

import com.cydeo.utilities.ConfigurationReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class MoneyGamingSubmitPage extends MoneyGamingBasePage {

    @FindBy(id = "title")
    public WebElement titleDropdown;

    @FindBy(id = "forename")
    public WebElement firstNameInputBox;

    @FindBy(name = "map(lastName)")
    public WebElement lastNameInputBox;

    @FindBy(xpath = "//input[@id='checkbox'][3]")
    public WebElement consentCheckbox;

    @FindBy(id = "form")
    public WebElement submitBtn;

    @FindBy(css = "[for='dob']")
    public WebElement requiredTxt;

    public void enterFirstName(){
        String firstName = ConfigurationReader.getProperties("firstName");
        firstNameInputBox.sendKeys(firstName);
    }

    public void enterLastName(){
        String lastName = ConfigurationReader.getProperties("lastName");
        lastNameInputBox.sendKeys(lastName);
    }

    public void selectTitle(){
        String title = ConfigurationReader.getProperties("title");
        Select select = new Select(titleDropdown);
        select.selectByValue(title);
    }

    public void selectConsentCheckbox(){
        consentCheckbox.click();
    }

    public void submitForm(){
        submitBtn.click();
    }

    public String fieldRequiredTxt(){
       return requiredTxt.getText();
    }

}
