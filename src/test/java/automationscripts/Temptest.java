package automationscripts;

import org.testng.annotations.Test;

import baseframework.BaseTest;
import baseframework.DriverFactory;
import pageobjects.ShadowDompages;

public class Temptest extends BaseTest {
	@Override
	public String getTestDataParser() {
		
		return "not required for this file";
	}

	@Test
	public void multipleshadowelements4() {
		
		 driver = DriverFactory.getInstance().getDriver();
		ShadowDompages shadowpage = new ShadowDompages(driver);
		shadowpage.multipleshadowElements();

	}

	 @Test
	public void multipleshadowelements5() {
		
		 driver = DriverFactory.getInstance().getDriver();
		ShadowDompages shadowpage = new ShadowDompages(driver);
		shadowpage.multipleshadowElements();

	}

}
