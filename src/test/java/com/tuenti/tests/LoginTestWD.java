package com.tuenti.tests;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.tuenti.page_objects.LoginPage;
import com.tuenti.DriverFactory;
import com.tuenti.config.DriverType;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;


public class LoginTestWD extends DriverFactory {
	public static String url_index= "https://www.tuenti.com/login";
	public static String userMSISDN="684262289";
	public static String NotRegistereduserMSISDN="666123456";
	public static String userEmail="ops_internacional+24@tuenti.com";
	public static String password="testtest";
	public static String homePageTittle="Recientes";
	public static String errorMessage="Alguno de tus datos no es correcto";
	public static String emptyFieldMessageFirefox="Rellene este campo.";
	public static String emptyFieldMessageChrome="Completa este campo";
	public static String emptyFieldMessageIE="Éste es un campo necesario";
    
	public void assertHomePage(WebDriver driver)
	{
		    WebDriverWait wait = new WebDriverWait(driver, 15, 100);
	        wait.until(ExpectedConditions.titleContains(homePageTittle));
	    	assertThat(driver.getTitle(), containsString(homePageTittle));
	}
	
	public void assertDataErrorMessage(WebDriver driver)
	{
		    WebDriverWait wait = new WebDriverWait(driver, 15, 100);
	        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("errorMessage"), errorMessage));
	    	assertThat(driver.findElement(By.id("errorMessage")).getText(), containsString(errorMessage));
	}
	
	public void assertEmptyFieldMessage(WebDriver driver, By fieldLocator)
	{      
		   String emptyFieldMessage=emptyFieldMessageFirefox;
           //Messages are different for each browser
           if(getDriverType()==DriverType.CHROME)
 	          emptyFieldMessage=emptyFieldMessageChrome;
           else
           if(getDriverType()==DriverType.IE)
	    	   emptyFieldMessage=emptyFieldMessageIE;
           
		   JavascriptExecutor js = (JavascriptExecutor)driver;
	       WebElement field = driver.findElement(fieldLocator);
	       //Check if field is empty
           Boolean is_valid = (Boolean)js.executeScript("return arguments[0].checkValidity();", field);
           //Check validation error message
           String message = (String)js.executeScript("return arguments[0].validationMessage;", field);
           Assert.assertTrue(!is_valid && message.equals(emptyFieldMessage));
	}
	
     //Testcase TC001
	@Test
    public void loginWithMSISDNandPasswordOK() throws Exception {
    	
        WebDriver driver=getDriver();
        driver.get(url_index);
        
        LoginPage loginp=new LoginPage();
        loginp.loginUsernamePassword(userMSISDN, password, true, driver);
        assertHomePage(driver);
    }
    
    //Testcase TC011
	@Test
    public void loginWitEmailandPasswordOK() throws Exception {
    	
        WebDriver driver=getDriver();
        driver.get(url_index);
        
        LoginPage loginp=new LoginPage();
        loginp.loginUsernamePassword(userEmail, password, true, driver);
        assertHomePage(driver);
       
    }
   //Testcase TC002
   @Test
   public void loginWithMSISDNandInvalidPassword() throws Exception {
    	
        WebDriver driver=getDriver();
        driver.get(url_index);
        
        LoginPage loginp=new LoginPage();
        loginp.loginUsernamePassword(userMSISDN, password+"_invalid", true, driver);
        assertDataErrorMessage(driver);
    }
   
   //Testcase TC003
   @Test
    public void loginWithNotRegisteredMSISDN() throws Exception {
    	
        WebDriver driver=getDriver();
        driver.get(url_index);
        
        LoginPage loginp=new LoginPage();
        loginp.loginUsernamePassword(NotRegistereduserMSISDN, password, true, driver);
        assertDataErrorMessage(driver);
    }
    
   //Testcase TC005
   @Test
   public void loginWithMSISDNandBlankPassword() throws Exception {
    	
        WebDriver driver=getDriver();
        driver.get(url_index);
        
        LoginPage loginp=new LoginPage();
        loginp.loginUsernamePassword(userMSISDN, "", true, driver);
        assertEmptyFieldMessage(driver, LoginPage.passwordLocator);
        
    }
  
}