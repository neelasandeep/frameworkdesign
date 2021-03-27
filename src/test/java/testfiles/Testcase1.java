package testfiles;

import java.sql.SQLException;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import commoncomponents.PropertiesUtility;
import dataconstants.Constants;
import dbUtil.CommonDBOperation;
import pageobjects.ShadowDompages;
import testbase.BaseTest;
import testbase.DriverFactory;

//@Listeners(listeners.TestListener.class)	
public class Testcase1 extends BaseTest {
	@Override
	public String getTestDataParser() {
		
		return "not required for this file";
	}

	@Test
	public void multipleshadowelements2() {
		
		WebDriver driver = DriverFactory.getInstance().getDriver();
		ShadowDompages shadowpage = new ShadowDompages(driver);
		shadowpage.multipleshadowElements();

	}

	 @Test
	public void multipleshadowelements3() {
		
		WebDriver driver = DriverFactory.getInstance().getDriver();
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
		ArrayList<String> result = dbcon.getEmployeeDetailsfromDB(PropertiesUtility.getProperty(Constants.DBQUERY));
		System.out.println(result);

	}

	

}
