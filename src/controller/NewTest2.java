package controller;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NewTest2 {
	
	public String ExpectedValue = null;
	public String ActualValue = null;

	WebDriver driver = null;
	
@BeforeTest
	public void runBeforeTest() {
		NewTest newTest = new NewTest();
		driver = newTest.launchBrowser();
		WebElement applyNow = driver.findElement(By.xpath("(//a[contains(text(),'Apply NOW!')])[2]"));
		  applyNow.click();

		
	}
  @Test
      public void verifyAllErrors() {
	  ExpectedValue ="This field is required";
	  WebElement CheckContinue = driver.findElement(By.id("checkit"));
	  CheckContinue.click();
	  WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label.error")));
	  ActualValue =driver.findElement(By.cssSelector("label.error")).getText().toString();
	  //assertEquals(ExpectedValue,ActualValue);
	  AssertJUnit.assertEquals(ActualValue, ExpectedValue);
	  
	  
	  
	  	}
  @Test
      public void verifyAllErrors2(){
	  
	  driver.findElement(By.className("login-box"))
		.findElement(By.id("applynowemail")).sendKeys("test@best.com");
	  WebElement CheckContinue = driver.findElement(By.id("checkit"));
	  CheckContinue.click();
	  driver.findElement(By.className("login-box"))
		.findElement(By.id("applynowemail")).click();
	  WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.id("f2submit")));
	  driver.findElement(By.id("f2submit")).click();
	  System.out.println("Buttonclicked");
			  /*
		TODO : Error message validation for sign up form
	    WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label.error")));
	    */
    	  
      }
  
  
 /* @AfterTest
	public void terminatetest() {
		driver.close();
	}	*/  	
}
		
