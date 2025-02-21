package com.orangehrm.testcases;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.orangehrm.basetest.BaseClass;
import com.orangehrm.factories.ExtentManager;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.util.ConfigReader;

public class LoginTest extends BaseClass {
	public LoginPage loginpage;

	@Test
	public void userValidlogin() throws InterruptedException {
		loginpage = new LoginPage();
		ExtentManager.getTest().log(Status.INFO, "Starting Login Test");

		String username = ConfigReader.getProperty("username");
		String password = ConfigReader.getProperty("password");

		loginpage.loginToApplication(username, password);
		
		 ExtentManager.getTest().log(Status.PASS, "Login Test Passed");

	}

	@Test
	public void userValidlogin1() throws InterruptedException {
		loginpage = new LoginPage();
		String username = ConfigReader.getProperty("usernamd");
		String password = ConfigReader.getProperty("password");

		loginpage.loginToApplication(username, password);

	}

	@Test
	public void userValidlogin2() throws InterruptedException {
		loginpage = new LoginPage();
		String username = ConfigReader.getProperty("userame");
		String password = ConfigReader.getProperty("password");

		loginpage.loginToApplication(username, password);

	}

}
