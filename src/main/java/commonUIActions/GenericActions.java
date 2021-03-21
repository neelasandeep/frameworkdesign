package commonUIActions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import testbase.DriverFactory;
import testbase.ExtentFactory;

public class GenericActions {
	ExtentTest logger;

	// Customized sendkeys method-> To log sendkeys message for every occ.
	public void sendKeys(WebElement element, String valueToBeSent) {
		try {
			logger = ExtentFactory.getInstance().getExtent();
			element.sendKeys(valueToBeSent);
			// log success message in exgent report
			logger.log(Status.PASS, "==> Ented value as: " + valueToBeSent);
		} catch (Exception e) {
			// log failure in extent
			logger.log(Status.FAIL, "Value enter in field: " + element + " is failed due to exception: " + e);
		}
	}

	// custom click method to log every click action in to extent report
	public void click(WebElement element,String fieldName) {
		try {
			logger = ExtentFactory.getInstance().getExtent();

			element.click();
			// log success message in extent report
			logger.log(Status.PASS, fieldName+"==> Clicked Successfully! ");
		} catch (Exception e) {
			// log failure in extent
			logger.log(Status.FAIL, "Unable to click on field: " +fieldName +" due to exception: "+e);
		}
	}

	// clear data from field
	public void clear(WebElement element,String fieldName) {
		try {
			logger = ExtentFactory.getInstance().getExtent();
			element.clear();
			Thread.sleep(250);
			logger.log(Status.PASS,   fieldName+"==> Data Cleared Successfully! ");
		} catch (Exception e) {
			logger.log(Status.FAIL, "Unable to clear Data on field: " +fieldName +" due to exception: "+e);

		}
	}

	// custom mouseHover
	public void moveToElement(WebElement element, String fieldName) {
		try {
			logger = ExtentFactory.getInstance().getExtent();
			JavascriptExecutor executor = (JavascriptExecutor) DriverFactory.getInstance().getDriver();
			executor.executeScript("arguments[0].scrollIntoView(true);", element);
			Actions actions = new Actions(DriverFactory.getInstance().getDriver());
			actions.moveToElement(element).build().perform();
			logger.log(Status.PASS, fieldName + fieldName+"==> Mouse hovered Successfully! ");
			Thread.sleep(1000);
		} catch (Exception e) {
			logger.log(Status.FAIL, "Unable to hover mouse on field: " + fieldName + " due to exception: " + e);

		}
	}

	// check if element is Present
	public boolean isElementPresent(WebElement element, String fieldName) {
		boolean flag = false;
		try {
			logger = ExtentFactory.getInstance().getExtent();
			flag = element.isDisplayed();
			logger.log(Status.PASS, fieldName + fieldName+"==> Presence of field is: "+ flag);
			return flag;
		} catch (Exception e) {
			logger.log(Status.FAIL,
					"Checking for presence of field: " + fieldName + " not tested due to exception: " + e);
			return flag;
		}
	}

	// Select dropdown value value by visibleText
	public void selectDropDownByVisibleText(WebElement element, String fieldName, String ddVisibleText)
			throws Throwable {
		try {
			logger = ExtentFactory.getInstance().getExtent();
			Select s = new Select(element);
			s.selectByVisibleText(ddVisibleText);
			logger.log(Status.PASS,
					fieldName + fieldName+"==> Dropdown Value Selected by visible text: "+ ddVisibleText);
		} catch (Exception e) {
			logger.log(Status.FAIL,
					"Dropdown value not selected for field: " + fieldName + "  due to exception: " + e);
		}
	}

	// Select dropdown value value by value
	public void selectDropDownByValue(WebElement element, String fieldName, String ddValue) throws Throwable {
		try {
			logger = ExtentFactory.getInstance().getExtent();
			Select s = new Select(element);
			s.selectByValue(ddValue);
			logger.log(Status.PASS,
					fieldName + "==> Dropdown Value Selected by visible text: " + ddValue);
		} catch (Exception e) {
			logger.log(Status.FAIL,
					"Dropdown value not selected for field: " + fieldName + "  due to exception: " + e);
		}
	}

	// String Asserts
	public void assertEqualsString(String expvalue, String actualValue, String locatorName) throws Throwable {
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

	// Get text from webelement
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

}
