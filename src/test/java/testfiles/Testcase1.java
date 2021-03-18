package testfiles;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import pageobjects.ShadowDompages;
import testbase.BaseTest;
import testbase.DriverFactory;


public class Testcase1 extends BaseTest{
	@Test
	public void multipleshadowelements2() {
		WebDriver driver=DriverFactory.getInstance().getDriver();
		ShadowDompages shadowpage = new ShadowDompages(driver);
		shadowpage.multipleshadowElements();
		System.out.println("second-->"+Thread.currentThread().getId());

	}
	@Test
	public void multipleshadowelements3() {
		WebDriver driver=DriverFactory.getInstance().getDriver();
		ShadowDompages shadowpage = new ShadowDompages(driver);
		shadowpage.multipleshadowElements();
		System.out.println("second-->"+Thread.currentThread().getId());

	}
	@Test
	public void multipleshadowelements4() {
		WebDriver driver=DriverFactory.getInstance().getDriver();
		ShadowDompages shadowpage = new ShadowDompages(driver);
		shadowpage.multipleshadowElements();
		System.out.println("second-->"+Thread.currentThread().getId());

	}

}
