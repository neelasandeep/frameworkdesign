package uiactions;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseframework.DriverFactory;
import baseframework.ExtentFactory;

public class GenericActions {
	ExtentTest logger;
	WebDriver driver;
	static final String DATE = "yyyy/MM/dd";

	public void sendKeys(WebElement element, String valueToBeSent) {
		try {
			logger = ExtentFactory.getInstance().getExtent();
			element.sendKeys(valueToBeSent);

			logger.log(Status.PASS, "==> Ented value as: " + valueToBeSent);
		} catch (Exception e) {

			logger.log(Status.FAIL, "Value enter in field: " + element + " is failed due to exception: " + e);
		}
	}

	public void click(WebElement element, String fieldName) {
		try {
			logger = ExtentFactory.getInstance().getExtent();

			element.click();
			logger.log(Status.PASS, fieldName + "==> Clicked Successfully! ");
		} catch (Exception e) {

			logger.log(Status.FAIL, "Unable to click on field: " + fieldName + " due to exception in: " + e);
		}
	}

	public void clear(WebElement element, String fieldName) {
		try {
			logger = ExtentFactory.getInstance().getExtent();
			element.clear();
			Thread.sleep(250);
			logger.log(Status.PASS, fieldName + "==> Data Cleared Successfully! ");
		} catch (Exception e) {
			logger.log(Status.FAIL, "Unable to clear Data on field: " + fieldName + " due to exception in the : " + e);

		}
	}

	public void moveToElement(WebElement element, String fieldName) {
		try {
			logger = ExtentFactory.getInstance().getExtent();
			JavascriptExecutor executor = (JavascriptExecutor) DriverFactory.getInstance().getDriver();
			executor.executeScript("arguments[0].scrollIntoView(true);", element);
			Actions actions = new Actions(DriverFactory.getInstance().getDriver());
			actions.moveToElement(element).build().perform();
			logger.log(Status.PASS, fieldName + fieldName + "==> Mouse hovered Successfully! ");
			Thread.sleep(1000);
		} catch (Exception e) {
			logger.log(Status.FAIL, "Unable to hover mouse on field: " + fieldName + " becuase of exception: " + e);

		}
	}

	public boolean isElementPresent(WebElement element, String fieldName) {
		boolean flag = false;
		try {
			logger = ExtentFactory.getInstance().getExtent();
			flag = element.isDisplayed();
			logger.log(Status.PASS, fieldName + fieldName + "==> Presence of field is: " + flag);
			return flag;
		} catch (Exception e) {
			logger.log(Status.FAIL,
					"Checking for presence of field: " + fieldName + " not tested due to exception: " + e);
			return flag;
		}
	}

	public void selectDropDownByVisibleText(WebElement element, String fieldName, String ddVisibleText)
			 {
		try {
			logger = ExtentFactory.getInstance().getExtent();
			Select s = new Select(element);
			s.selectByVisibleText(ddVisibleText);
			logger.log(Status.PASS,
					fieldName + fieldName + "==> Dropdown Value Selected by visible text: " + ddVisibleText);
		} catch (Exception e) {
			logger.log(Status.FAIL, "Dropdown value not selected for field: " + fieldName + "  due to exception: " + e);
		}
	}

	public void selectDropDownByValue(WebElement element, String fieldName, String ddValue)  {
		try {
			logger = ExtentFactory.getInstance().getExtent();
			Select s = new Select(element);
			s.selectByValue(ddValue);
			logger.log(Status.PASS, fieldName + "==> Dropdown Value Selected by visible text: " + ddValue);
		} catch (Exception e) {
			logger.log(Status.FAIL, "Dropdown value not selected for field: " + fieldName + "  due to exception: " + e);
		}
	}

	public void assertEqualsString(String expvalue, String actualValue, String locatorName)  {
		try {
			if (actualValue.equals(expvalue)) {
				ExtentFactory.getInstance().getExtent().log(Status.PASS, "String Assertion is successful on field "
						+ locatorName + " Expected value was: " + expvalue + " actual value is: " + actualValue);
			} else {
				ExtentFactory.getInstance().getExtent().log(Status.FAIL, "String Assertion FAILED on field "
						+ locatorName + " Expected value was: " + expvalue + " actual value is: " + actualValue);
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			Assert.assertTrue(false, e.toString());
		}
	}

	public String getText(WebElement element, String fieldName) {
		String text = "";
		try {
			text = element.getText();
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName + "==> Text retried is: " + text);
			return text;
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					fieldName + "==> Text not retried due to exception: " + e);

		}
		return text;
	}

	public Wait<WebDriver> fluentWait() {
		driver = DriverFactory.getInstance().getDriver();
		return new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(60))
				.pollingEvery(Duration.ofSeconds(10)).ignoring(NoSuchElementException.class)
				.ignoring(ElementNotVisibleException.class).ignoring(Exception.class);

	}

	public void waitForElementAndThenClick(WebElement element) {
		Wait<WebDriver> waitFor = fluentWait();
		WebElement ele = waitFor.until(ExpectedConditions.elementToBeClickable(element));

		ele.click();
	}

	public void waitForElementAndThenClear(WebElement element) {
		Wait<WebDriver> waitFor = fluentWait();
		WebElement ele = waitFor.until(ExpectedConditions.elementToBeClickable(element));
		ele.clear();
	}

	public void waitForElementSetFocusAndClear(WebElement element) {
		Wait<WebDriver> waitFor = fluentWait();
		WebElement ele = waitFor.until(ExpectedConditions.elementToBeClickable(element));
		ele.click();
		ele.clear();
	}

	public void waitForElementAndUpdateText(WebElement element, String text) {

		Wait<WebDriver> waitFor = fluentWait();
		WebElement ele = waitFor.until(ExpectedConditions.elementToBeClickable(element));
		ele.clear();
		ele.sendKeys(text);
	}

	public boolean verifyElementIsClickable(WebElement element) {

		Wait<WebDriver> waitFor = fluentWait();
		waitFor.until(ExpectedConditions.elementToBeClickable(element));
		return true;
	}

	public String getTodayDateAsString() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE);
		LocalDateTime localdate = LocalDateTime.now();
		return dtf.format(localdate);
	}

	public void setTheFieldValueDropdownArrowEnter(WebElement element, String text) {
		fluentWait().until(ExpectedConditions.elementToBeClickable(element));
		element.click();
		element.clear();
		element.sendKeys(text);
		waitForBrowser(2);
		element.sendKeys(Keys.DOWN, Keys.ENTER);
	}

	public String waitForElementAndThenGetText(WebElement element) {
		WebElement ele = fluentWait().until(ExpectedConditions.visibilityOf(element));
		return ele.getText();
	}

	public void waitForElementAndSelectTheDropdownByVisibleText(WebElement element, String text) {
		WebElement ele = fluentWait().until(ExpectedConditions.elementToBeClickable(element));
		Select select = new Select(ele);
		select.selectByVisibleText(text);
	}

	public void waitForElementAndSelectTheDropdownByValue(WebElement element, String value) {
		WebElement ele = fluentWait().until(ExpectedConditions.elementToBeClickable(element));
		Select select = new Select(ele);
		select.selectByValue(value);
	}

	public void waitForElementAndSelectTheDropdownByIndex(WebElement element, int index) {
		WebElement ele = fluentWait().until(ExpectedConditions.elementToBeClickable(element));
		Select select = new Select(ele);
		select.selectByIndex(index);
	}

	public List<WebElement> waitForElementAndgetOptionsFromComboBox(WebElement element) {
		WebElement ele = fluentWait().until(ExpectedConditions.elementToBeClickable(element));
		return new Select(ele).getOptions();

	}

	public boolean wiatForElementAndCheckForVisibiltyOfElements(WebElement element) {
		WebElement ele = fluentWait().until(ExpectedConditions.visibilityOf(element));
		return ele.isDisplayed();
	}

	public void waitForBrowser(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {

			logger.log(Status.FAIL, "Dropdown value not selected for field: due to exception: " + e);
		}
	}

	public void swithToNewTabCloseTheCurrentTab() {
		WebDriver switchdriver = DriverFactory.getInstance().getDriver();
		String currentWindow = switchdriver.getWindowHandle();
		waitForBrowser(2);
		Set<String> tabs = switchdriver.getWindowHandles();
		tabs.remove(currentWindow);
		switchdriver.close();
		switchdriver.switchTo().window((String) tabs.toArray()[0]);

	}

	public void switchToMaximumNumberWindow(WebDriver driver) {
		Set<String> tabs = driver.getWindowHandles();
		String val = String.valueOf(tabs.size() - 1);
		driver.switchTo().window(val);
	}

	public void switchToSpecificWindow(WebDriver driver, int val) {
		Set<String> tabs = driver.getWindowHandles();
		driver.switchTo().window((String) tabs.toArray()[val]);

	}

	public String generateRandomUID() {
		return RandomStringUtils.randomNumeric(6);
	}

	public void waitForFrameAndMoveToFrame(String frameId) {
		fluentWait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameId));

	}

	public boolean isTextpresent(WebElement element, String text) {
		WebElement ele = fluentWait().until(ExpectedConditions.visibilityOf(element));
		return ele.getText().equalsIgnoreCase(text);
	}

	public boolean verifyIsSelect(WebElement element) {
		WebElement ele = fluentWait().until(ExpectedConditions.visibilityOf(element));
		return ele.isSelected();
	}

	public void doubleClick(WebElement element) {
		Actions action = new Actions(DriverFactory.getInstance().getDriver());
		action.moveToElement(element).doubleClick().build().perform();

	}

	public void rightClick(WebElement element) {
		Actions action = new Actions(DriverFactory.getInstance().getDriver());
		action.moveToElement(element).contextClick(element).perform();

	}

	public String randomString() {

		return RandomStringUtils.randomAlphanumeric(10);
	}

	public void acceptAlert(WebDriver driver) {
		fluentWait().until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
	}

	public void dismissAlert(WebDriver driver) {
		fluentWait().until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().dismiss();
	}

	public String acceptAlertGetMessage(WebDriver driver) {
		String alertText;
		fluentWait().until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		alertText = alert.getText();
		alert.accept();
		return alertText;
	}

	public void scrollToElement(WebDriver driver, WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void scrollToEndOfpage(WebDriver driver, WebElement element) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)", element);
	}

	public void scrollToTopOfpage(WebDriver driver, WebElement element) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-250)", element);
	}

	public void javaScriptClick(WebElement element, WebDriver driver) {
		WebElement ele = fluentWait().until(ExpectedConditions.elementToBeClickable(element));
		((JavascriptExecutor) driver).executeScript("arguments[0].click", ele);

	}

	public List<String> gettextFromListOFWebElements(List<WebElement> webElements) {
		List<String> values = new ArrayList<>();
		for (WebElement ele : webElements) {
			values.add(ele.getText());
		}
		return values;
	}
}
