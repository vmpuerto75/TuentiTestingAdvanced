package com.tuenti.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    public static By userLocator = By.id("user");
    public static By passwordLocator = By.id("password");
    public static By submitButtonLocator = By.id("submit");
    public static By rememberMeLocator= By.id("rememberMe");
    public static By rememberMeLabelLocator= By.xpath("//label[@for=\"rememberMe\"]");

    public void loginUsernamePassword(String username, String password, boolean rememberme, WebDriver driver) {

        driver.findElement(userLocator).sendKeys(username);
        driver.findElement(passwordLocator).sendKeys(password);
        if(!(rememberme == driver.findElement(rememberMeLocator).isSelected()))
        {   //Click on the label associated with the checkbox
        	driver.findElement(rememberMeLabelLocator).click(); 
        }
        driver.findElement(submitButtonLocator).click();
    }
}
