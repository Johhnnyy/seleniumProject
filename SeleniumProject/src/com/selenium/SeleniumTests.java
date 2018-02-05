package com.selenium;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;


public class SeleniumTests{
	private static final String USERNAME = "dhiraj_admin";
	private static final String PASSWORD = "Wipro@123";
	private static final String PROJECT = "Default";
	String CLOUDURL = "https://sales.experitest.com/wd/hub/";
	//PlatformType platform;
	//String testName;
	WebDriver driver;

	@BeforeMethod
	@Parameters({"deviceQuery","testname"})
	public void setUp(String deviceQuery, String testName) throws Exception{	
		getDriver(deviceQuery, testName, true);
	}

	@Test

	public void test(){
		try {

			driver.get("https://www.google.com");

			Thread.sleep(8000);
			WebElement searchBar = driver.findElement(By.id("lst-ib"));
			searchBar.click();
			Thread.sleep(5000);
			searchBar.sendKeys("Jerusalem");
			driver.findElement(By.xpath("//*[@id=\"hplogo\"]")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[3]/center/input[1]")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[contains(text(), 'History of Jerusalem')]")).click();
			Thread.sleep(5000);
			System.out.println("url of page is: " + driver.getCurrentUrl());
			System.out.println("title of page is: " + driver.getTitle());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void tearDown(){
		driver.quit();
	}



	public void getDriver(String platform, String testName, boolean generateReport) throws MalformedURLException {
		//Set Browser Type
		DesiredCapabilities dc = new DesiredCapabilities();

		if (platform.equalsIgnoreCase("chrome")) {
			dc = DesiredCapabilities.chrome();
		} else if (platform.equalsIgnoreCase("firefox")) {
			dc = DesiredCapabilities.firefox();
		} else if (platform.equalsIgnoreCase("ie")) {
			dc = DesiredCapabilities.internetExplorer();
		} 
		//Set Grid capabilities
		dc.setCapability("user", USERNAME);
		dc.setCapability("password", PASSWORD);
		dc.setCapability("project", PROJECT);
		dc.setCapability("generateReport", generateReport);
		dc.setCapability("testName", testName);
		String val = System.getenv("BUILD_NUMBER");
		dc.setCapability("build", val);
		dc.setCapability("testType", "Desktop");
		this.driver = new RemoteWebDriver(new URL(CLOUDURL), dc);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
}
