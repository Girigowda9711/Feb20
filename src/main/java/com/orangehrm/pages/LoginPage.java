package com.orangehrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orangehrm.factories.DriverFactory;

public class LoginPage {

	private WebDriver driver;

	public LoginPage() {
		this.driver = DriverFactory.getDriver();
		PageFactory.initElements(driver, this);
	}

	

	@FindBy(xpath = "//input[@name='username']")
	private WebElement txtUsername;

	@FindBy(name = "password")
	private WebElement txtPassword;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement btnLogin;

	@FindBy(xpath = "//p[@class='oxd-text oxd-text--p orangehrm-login-forgot-header']")
	private WebElement forgotPasswordLink;

	public void enterUsername(String username) {
		txtUsername.sendKeys(username);
	}

	public void enterPassword(String password) {
		txtPassword.sendKeys(password);
	}

	public void clickLogin() {
		btnLogin.click();
	}

	public boolean isForgotPasswordDisplayed() {
		return forgotPasswordLink.isDisplayed();
	}

	public void loginToApplication(String username, String password) throws InterruptedException {
		enterUsername(username);

		enterPassword(password);
		clickLogin();
	}

}
