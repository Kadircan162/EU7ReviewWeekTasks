package com.cydeo.tasks;

import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstClass {
    public static void main(String[] args) {

        System.out.println("Hello World");
        Faker faker = new Faker();
        System.out.println(faker.harryPotter().character());
        System.out.println(faker.name().fullName());
        System.out.println(faker.finance().creditCard(CreditCardType.AMERICAN_EXPRESS));

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://google.com");
    }
}
