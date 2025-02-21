package com.orangehrm.factories;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

public class TestListener implements ITestListener {

	private static ExtentReports extent;
	private WebDriver driver;

	@Override
	public void onStart(ITestContext context) {
		extent = ExtentManager.getInstance();
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest test = extent.createTest(result.getMethod().getMethodName());
		ExtentManager.setTest(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentManager.getTest().pass("Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		driver = DriverFactory.getDriver();
		String screenshotPath = captureScreenshot(driver, result.getMethod().getMethodName());

		try {
			ExtentManager.getTest().fail("Test Failed: " + result.getThrowable(),
					MediaEntityBuilder.createScreenCaptureFromPath("."+screenshotPath).build());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentManager.getTest().skip("Test Skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

	private String captureScreenshot(WebDriver driver, String testName) {
		String screenshotPath = "./screenshots/" + testName + ".png";
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File(screenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return screenshotPath;
	}

}
