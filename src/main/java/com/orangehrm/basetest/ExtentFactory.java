package com.orangehrm.basetest;

import com.aventstack.extentreports.ExtentTest;

public class ExtentFactory {
	
	private static ThreadLocal<ExtentTest> extent = new ThreadLocal<ExtentTest>();
	
	
	public static ExtentTest getExtent() {
		return extent.get();
	}
	
	public void setExtent(ExtentTest extentTestObject) {
		extent.set(extentTestObject);
	}
	
	public void removeExtentObject() {
		extent.remove();
	}

}
