package automationScripts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import baseFrameWork.BaseTest;
import models.testDataModel.ExcelData;

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
