package com.orangehrm.util;

import java.io.File;
import java.time.Duration;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orangehrm.factories.DriverFactory;

public class CommonUtils {
	
	
	private static final Logger logger = LogManager.getLogger(CommonUtils.class);

	
	
	
	public void waitForElementToBeClickable(WebElement element, int timeout) {
	    WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(timeout));
	    wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	
	
	
	public void selectByVisibleText(WebElement element, String text) {
	    Select select = new Select(element);
	    select.selectByVisibleText(text);
	}
	
	public void selectByIndex(WebElement element, int index) {
	    Select select = new Select(element);
	    select.selectByIndex(index);
	}
	public void selectByValue(WebElement element, String value) {
	    Select select = new Select(element);
	    select.selectByValue(value);
	}

	public boolean isElementDisplayed(WebElement element) {
	    try {
	        return element.isDisplayed();
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	}

	public boolean isElementEnabled(WebElement element) {
	    return element.isEnabled();
	}

	public void acceptAlert() {
	    Alert alert = DriverFactory.getDriver().switchTo().alert();
	    alert.accept();
	}

	public void dismissAlert() {
	    Alert alert = DriverFactory.getDriver().switchTo().alert();
	    alert.dismiss();
	}
	
	public String getAlertText() {
	    Alert alert = DriverFactory.getDriver().switchTo().alert();
	    return alert.getText();
	}

	public void scrollToElement(WebElement element) {
	    JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
	    js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void switchToWindowByTitle(String title) {
	    for (String window : DriverFactory.getDriver().getWindowHandles()) {
	    	DriverFactory.getDriver().switchTo().window(window);
	        if (DriverFactory.getDriver().getTitle().equals(title)) {
	            break;
	        }
	    }
	}

	public void switchToIFrameByIndex(int index) {
	    DriverFactory.getDriver().switchTo().frame(index);
	}


	public void switchToIFrameByName(String nameOrId) {
	    DriverFactory.getDriver().switchTo().frame(nameOrId);
	}


    public void waitForElementToBeVisible(WebElement element, int timeout) {
        logger.info("Waiting for element to be visible: " + element);
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clickElement(WebElement element) {
        logger.info("Clicking on element: " + element);
        waitForElementToBeVisible(element, 10);
        element.click();
    }

    public void enterText(WebElement element, String text) {
        logger.info("Entering text: '" + text + "' into element: " + element);
        waitForElementToBeVisible(element, 10);
        element.clear();
        element.sendKeys(text);
    }

    public String captureScreenshot(String testName) {
        logger.info("Capturing screenshot for test case: " + testName);
        TakesScreenshot ts = (TakesScreenshot) DriverFactory.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        String dest = "screenshots/" + testName + ".png";
        source.renameTo(new File(dest));
        return dest;
    }


	

}
