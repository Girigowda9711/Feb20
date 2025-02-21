package com.orangehrm.basetest;

import com.aventstack.extentreports.ExtentTest;

public class ExtentFactory {
	
	private static ThreadLocal<ExtentTest> extent = new ThreadLocal<ExtentTest>();
	
	
	public static ExtentTest getExtent() {
		return extent.get();
	}
	
	public static void setExtent(ExtentTest extentTestObject) {
		extent.set(extentTestObject);
	}
	
	public static void removeExtentObject() {
		extent.remove();
	}

}
