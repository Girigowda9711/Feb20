package com.orangehrm.testcases;

import org.testng.annotations.Test;

import com.orangehrm.basetest.BaseClass;
import com.orangehrm.basetest.DriverFactory;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.util.ConfigReader;

public class LoginTest extends BaseClass {
	public LoginPage loginpage;

	@Test
	public void userValidlogin() throws InterruptedException {
		loginpage = new LoginPage();
		String username = ConfigReader.getProperty("username");
		String password = ConfigReader.getProperty("password");

		loginpage.loginToApplication(username, password);

	}

	@Test
	public void userValidlogin1() throws InterruptedException {
		loginpage = new LoginPage();
		String username = ConfigReader.getProperty("username");
		String password = ConfigReader.getProperty("password");

		loginpage.loginToApplication(username, password);

	}

	@Test
	public void userValidlogin2() throws InterruptedException {
		loginpage = new LoginPage();
		String username = ConfigReader.getProperty("username");
		String password = ConfigReader.getProperty("password");

		loginpage.loginToApplication(username, password);

	}

}
