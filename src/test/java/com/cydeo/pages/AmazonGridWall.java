package com.cydeo.pages;

import com.cydeo.utilities.DriverSetup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AmazonGridWall {

    public AmazonGridWall(){
        PageFactory.initElements(DriverSetup.getDriver(), this);
    }

   @FindBy(css="[class='s-image']")
   WebElement element;


}
