package automationscripts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import baseframework.BaseTest;
import models.testdatamodel.ExcelData;

public class ExcelTest extends BaseTest {
	private static Logger logger = LogManager.getLogger();

	@Override
	public String getTestDataParser() {

		return "xlsx";
	}

	@Test(dataProvider = "testdata")
	public void usertest(ExcelData data) {
		logger.info(data.getTestDataAsString("username"));
		logger.info(data.getTestDataAsString("password"));

	}

	@Test(dataProvider = "testdata")
	public void usertest2(ExcelData data) {
		logger.info(data.getTestDataAsString("username"));
		logger.info(data.getTestDataAsString("password"));

	}
	//@Test(dataProvider = "testdata")
	public void normalexcel(ExcelData data) {
		logger.info(data.getTestDataAsString("username"));
		logger.info(data.getTestDataAsString("testcaseid"));

	}

}
