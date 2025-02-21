package com.orangehrm.factories;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	
	 private static ExtentReports extent;
	    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

	    public static ExtentReports getInstance() {
	        if (extent == null) {
	            extent = new ExtentReports();
	            ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
	            extent.attachReporter(spark);
	        }
	        return extent;
	    }

	    public static void setTest(ExtentTest test) {
	        testThread.set(test);
	    }

	    public static ExtentTest getTest() {
	        return testThread.get();
	    }

	    public static void removeTest() {
	        testThread.remove();
	    }

}
