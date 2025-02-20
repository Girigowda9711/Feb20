package com.orangehrm.basetest;

import java.time.Duration;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.orangehrm.util.ConfigReader;

public class BaseClass {

	BrowserFactory bf = new BrowserFactory();

	@BeforeMethod
	public void setUp() {
		String browser = ConfigReader.getProperty("browser");
		String url = ConfigReader.getProperty("url");

		DriverFactory.setDriver(bf.initialize(browser));
		DriverFactory.getDriver().manage().window().maximize();
		DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		DriverFactory.getDriver().get(url);

	}

	@AfterMethod
	public void tearDown() {
		DriverFactory.closeDriver();

	}
}
