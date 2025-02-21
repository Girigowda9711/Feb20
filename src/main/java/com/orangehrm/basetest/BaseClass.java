package com.orangehrm.basetest;

import java.time.Duration;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.orangehrm.factories.BrowserFactory;
import com.orangehrm.factories.DriverFactory;
import com.orangehrm.factories.ExtentManager;
import com.orangehrm.util.ConfigReader;

public class BaseClass {

	BrowserFactory bf = new BrowserFactory();
	
	 @BeforeSuite
	    public void setupSuite() {
	        ExtentManager.getInstance(); // Initialize Extent Reports
	    }

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
	
	 @AfterSuite
	    public void tearDownSuite() {
	        ExtentManager.getInstance().flush(); // Ensure final report is saved
	    }
}
