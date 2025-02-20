package com.orangehrm.basetest;

import org.openqa.selenium.WebDriver;

public class DriverFactory {
	
	private static ThreadLocal<WebDriver> driver=new ThreadLocal<>();
	
	
	public static WebDriver getDriver() {
		return driver.get();
	}
	
	public static void setDriver(WebDriver setdriver) {
		driver.set(setdriver);
		
	}
	
	
	public static void closeDriver() {
		if(driver.get()!=null) {
			driver.get().quit();
			driver.remove();
		}
	}
	
	
		
		
	
}
