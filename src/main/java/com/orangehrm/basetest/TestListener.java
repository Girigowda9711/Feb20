package com.orangehrm.basetest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class TestListener implements ITestListener {
	
	static ExtentReports report;
	   ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		
		//before each test case
				test = report.createTest(result.getMethod().getMethodName());
				ExtentFactory.setExtent(test);
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentFactory.getExtent().log(Status.PASS, "Test Case: "+result.getMethod().getMethodName()+ " is Passed.");
		ExtentFactory.removeExtentObject();
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		ExtentFactory.getExtent().log(Status.FAIL, "Test Case: "+result.getMethod().getMethodName()+ " is Failed.");
		ExtentFactory.getExtent().log(Status.FAIL, result.getThrowable());
		
		//add screenshot for failed test.
		File src = ((TakesScreenshot)DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
		Date date = new Date();
		String actualDate = format.format(date);
		
		String screenshotPath = System.getProperty("user.dir")+
				"/Reports/Screenshots/"+actualDate+".jpeg";
		File dest = new File(screenshotPath);
		
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ExtentFactory.getExtent().addScreenCaptureFromPath(screenshotPath, "Test case failure screenshot");
		ExtentFactory.removeExtentObject();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentFactory.getExtent().log(Status.SKIP, "Test Case: "+result.getMethod().getMethodName()+ " is skipped.");
		ExtentFactory.removeExtentObject();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		try {
			 report = ExtentReportNG.setupExtentReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onFinish(ITestContext context) {
		report.flush();
	}

}
