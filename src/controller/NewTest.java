package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NewTest {

	public String ExpectedValue = null;
	public String ActualValue = null;

	WebDriver driver = null;

	@BeforeTest
	public WebDriver launchBrowser() {
		BrowserDriver browserDriver = new BrowserDriver();
		// driver = browserDriver.getChromeDriver();
		driver = browserDriver.getFireFoxDriver();
		browserDriver.setBaseUrl("https://www.easyfinancial.com/");
		driver.get(browserDriver.getBaseUrl());
		return driver;
	}

	@Test(priority = 1)
	public void VerifyHomepageTitle() {

		String ExpectedValue = "easyfinancial Services offers Personal Loans and Prepaid Cards";
		String ActualValue = driver.getTitle();
		AssertJUnit.assertEquals(ActualValue, ExpectedValue);
		driver.findElement(By.id("top_menu")).findElement(By.id("apply_now"))
				.click();
	}

	@Test(priority = 1)
	public void verifyInvalidEmail() {
		// Entring invalid email id
		driver.findElement(By.className("login-box"))
				.findElement(By.id("applynowemail")).sendKeys("gyasd");
		driver.findElement(By.className("login-box"))
				.findElement(By.className("bottom_actions"))
				.findElement(By.id("checkit")).click();

		// getting error text for invalid id

		WebElement loginBox = driver.findElement(By.className("login-box"));
		WebElement errorDiv = loginBox.findElement(By
				.cssSelector(".subwrap1 label.error"));
		String actualErrorMessage = errorDiv.getText().toString();
		AssertJUnit.assertEquals("Please enter a valid email address",
				actualErrorMessage);
		System.out.println("Success");
	}

	@Test(priority = 1)
	public void verifyValidEmail() {

		String[] emailIDs = { "user@domain.com", "user@domain.co.in",
				"user.name@domain.com", "user?name@domain.co.in",
				"user'name@domain.co.in" };

		Pattern pattern = Pattern
				.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
						+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

		for (int i = 0; i < emailIDs.length; i++) {
			driver.findElement(By.className("login-box"))
					.findElement(By.id("applynowemail")).clear();
			driver.findElement(By.className("login-box"))
					.findElement(By.id("applynowemail")).sendKeys(emailIDs[i]);
			Matcher matcher = pattern.matcher(emailIDs[i]);

			if (matcher.matches()) {
				System.out.println("VAlid Email " + emailIDs[i]);
			} else {
				System.out.println("invalid Email" + emailIDs[i]);
			}

		}
		driver.findElement(By.className("login-box"))
				.findElement(By.id("applynowemail")).clear();
	}

	@Test(priority = 2)
	public void verifyConinue() {

		driver.findElement(By.className("login-box"))
				.findElement(By.id("applynowemail")).sendKeys("test@best.com");

		WebElement clickContinue = driver
				.findElement(By.className("login-box"))
				.findElement(By.className("bottom_actions"))
				.findElement(By.id("checkit"));
		clickContinue.click();

	}

	@Test(priority = 3)
	public void verifyAllErrors() {

		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.id("f2submit")));
		WebElement clickStartApplicationNow = driver.findElement(By
				.id("f2submit"));
		clickStartApplicationNow.click();
		/*
		 * TODO : Error message validation WebDriverWait waitForErrors = new
		 * WebDriverWait(driver, 5);
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By
		 * .id("f2submit")));
		*/ 
	}

	@Test(priority = 4)
	public void verifyFirstName() {

		String[] fname = { "", "9", "Test", "Silvana Koch-Mehrin",
				"François Hollande" };

		Pattern pattern = Pattern.compile("^[\\p{L} .'-]+$");

		for (int i = 0; i < fname.length; i++) {
			driver.findElement(By.id("f_name")).clear();
			driver.findElement(By.id("f_name")).sendKeys(fname[i]);
			Matcher matcher = pattern.matcher(fname[i]);

			if (matcher.matches()) {
				System.out.println("VAlid First Name " + fname[i]);
			} else {
				System.out.println("invalid First Name" + fname[i]);
			}

		}

	}

	@Test(priority = 4)
	public void verifyLastName() {

		String[] lname = { "", "9", "Test", "Silvana Koch-Mehrin",
				"François Hollande" };

		Pattern pattern = Pattern.compile("^[\\p{L} .'-]+$");

		for (int i = 0; i < lname.length; i++) {
			driver.findElement(By.id("l_name")).sendKeys(lname[i]);
			Matcher matcher = pattern.matcher(lname[i]);

			if (matcher.matches()) {
				System.out.println("VAlid First Name " + lname[i]);
			} else {
				System.out.println("invalid First Name" + lname[i]);
			}

		}
		driver.findElement(By.id("l_name")).clear();
	}

	@Test(priority = 3)
	public void phoneType() {
		driver.findElement(By.name("phoneType"))
				.findElement(By.cssSelector("option[value=\"HOME\"]"))
				.isSelected();
		driver.findElement(By.name("phoneType"))
				.findElement(By.cssSelector("option[value=\"MOBILE\"]"))
				.isSelected();
		// TODO: Assert values

	}

	@Test(priority = 3)
	public void phoneNumber() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone")));
		WebElement phoneNo = driver.findElement(By.className("phone"))
				.findElement(By.id("phone"));
		phoneNo.clear(); // Masked input has to be cleared and clicked first
							// before sending keys to work both on Firefox and
							// Chrome
		phoneNo.click();
		phoneNo.sendKeys("1234567890");
		System.out.println("Phone No Validation Done");

	}

	@Test(priority = 3)
	public void postalCode() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.name("postalCode")));
		WebElement postalCode = driver.findElement(By.name("postalCode"));
		postalCode.isDisplayed();
		postalCode.clear(); // Masked input has to be cleared and clicked first
							// before sending keys to work both on Firefox and
							// Chrome
		postalCode.click();
		postalCode.sendKeys("t0o8h7");
		System.out.println("Postal Code Validation Done");

	}

	/*
	 * private boolean isElementPresent(By by) { try { driver.findElement(by);
	 * return true; } catch (NoSuchElementException e) { return false; } }
	 */
	@AfterTest
	public void terminatetest() {
		driver.close();
	}

}
