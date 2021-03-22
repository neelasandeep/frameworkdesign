package testfiles;

import java.sql.SQLException;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import commoncomponents.PropertiesUtility;
import dataconstants.Constants;
import dbUtil.MYSQLDB;
import dbUtil.DataBase;
import pageobjects.ShadowDompages;
import testbase.BaseTest;
import testbase.DriverFactory;
import testbase.ExtentFactory;

public class Testcase1 extends BaseTest {

	// @Test
	public void multipleshadowelements2() {
		logger = ExtentFactory.getInstance().getExtent();
		logger.info("cheking second method");
		logger.info("second-->" + Thread.currentThread().getId());
		WebDriver driver = DriverFactory.getInstance().getDriver();
		ShadowDompages shadowpage = new ShadowDompages(driver);
		shadowpage.multipleshadowElements();

	}

	// @Test
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

	@Test
	public void dabcheck() throws SQLException {
		DataBase dbcon = new MYSQLDB();
		ArrayList<ArrayList<String>> hm = dbcon.getSqlResultInMap(PropertiesUtility.getProperty(Constants.DBQUERY));
		for (int i = 0; i < hm.size(); i++) {
			for (int j = 0; j < hm.get(i).size(); j++)
				System.out.println(hm.get(i).get(j));
		}

	}

}
