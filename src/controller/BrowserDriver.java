package controller;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserDriver {

	//public final static String FIREFOX = "firefox";
	public String baseUrl;
	
	WebDriver driver = null;
	// public WebDriver d = new FirefoxDriver();
	// Firefox is default driver

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public WebDriver getChromeDriver() {
		System.setProperty("webdriver.chrome.driver", "E://chromedriver.exe");
		driver=new ChromeDriver();
		return driver;
	}

	public WebDriver getFireFoxDriver() {
		driver = new FirefoxDriver();
		return driver;
	}

}
