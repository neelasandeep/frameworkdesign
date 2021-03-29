package automationScripts;

import java.sql.SQLException;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import baseFrameWork.BaseTest;
import baseFrameWork.DriverFactory;
import constants.ApplicationConstants;
import dataBases.CommonDBOperation;
import pageobjects.ShadowDompages;
import utilities.PropertiesUtility;

//@Listeners(listeners.TestListener.class)	
public class Testcase1 extends BaseTest {
	@Override
	public String getTestDataParser() {
		
		return "not required for this file";
	}

	@Test
	public void multipleshadowelements2() {
		
		 driver = DriverFactory.getInstance().getDriver();
		ShadowDompages shadowpage = new ShadowDompages(driver);
		shadowpage.multipleshadowElements();

	}

	 @Test
	public void multipleshadowelements3() {
		
		 driver = DriverFactory.getInstance().getDriver();
		ShadowDompages shadowpage = new ShadowDompages(driver);
		shadowpage.multipleshadowElements();

	}

//	@Test
	public void multipleshadowelements4() {
		
		WebDriver driver = DriverFactory.getInstance().getDriver();
		ShadowDompages shadowpage = new ShadowDompages(driver);
		shadowpage.multipleshadowElements();

	}

	// @Test
	public void dabcheck() throws SQLException {

		CommonDBOperation dbcon = new CommonDBOperation();
		ArrayList<String> result = dbcon.getEmployeeDetailsfromDB(PropertiesUtility.getProperty(ApplicationConstants.DBQUERY));
		System.out.println(result);

	}

	

}
