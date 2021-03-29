package automationScripts;

import org.testng.annotations.Test;

import baseFrameWork.BaseTest;
import baseFrameWork.DriverFactory;
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
