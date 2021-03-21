package testfiles;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import pageobjects.ShadowDompages;
import testbase.BaseTest;
import testbase.DriverFactory;
import testbase.ExtentFactory;

public class Testcase1 extends BaseTest {

	@Test
	public void multipleshadowelements2() {
		logger = ExtentFactory.getInstance().getExtent();
		logger.info("cheking second method");
		logger.info("second-->" + Thread.currentThread().getId());
		WebDriver driver = DriverFactory.getInstance().getDriver();
		ShadowDompages shadowpage = new ShadowDompages(driver);
		shadowpage.multipleshadowElements();

	}

	//@Test
	public void multipleshadowelements3() {
		logger = ExtentFactory.getInstance().getExtent();
		logger.info("cheking Third method");
		logger.info("second-->" + Thread.currentThread().getId());
		WebDriver driver = DriverFactory.getInstance().getDriver();
		ShadowDompages shadowpage = new ShadowDompages(driver);
		shadowpage.multipleshadowElements();

	}

//	@Test
	public void multipleshadowelements4() {
		logger = ExtentFactory.getInstance().getExtent();
		logger.info("cheking 4rth method");
		logger.info("second-->" + Thread.currentThread().getId());
		WebDriver driver = DriverFactory.getInstance().getDriver();
		ShadowDompages shadowpage = new ShadowDompages(driver);
		shadowpage.multipleshadowElements();

	}

}
