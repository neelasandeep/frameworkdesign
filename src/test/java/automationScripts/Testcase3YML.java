package automationScripts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import baseFrameWork.BaseTest;
import models.testDataModel.JsonData;

public class Testcase3YML extends BaseTest{
	private static Logger logger = LogManager.getLogger();

	@Override
	public String getTestDataParser() {

		return "yml";
	}
	
	@Test(dataProvider = "testdata")
	public void yamldatacheck(JsonData data) {
		logger.info(data.getValue("ContentType"));
		logger.info(data.getValue("user"));
		

	}
	

	@Test(dataProvider = "testdata")
	public void secondyamlCheck(JsonData data) {
		logger.info(data.getValue("ContentType"));
		logger.info(data.getValue("user"));
		logger.info(data.getValue("check"));

	}
	@Test(dataProvider = "testdata")
	public void thirdyamlCheck(JsonData data) {
		logger.info(data.getValue("ContentType"));
		logger.info(data.getValue("user"));
		logger.info(data.getValue("check"));

	}
	@Test(dataProvider = "testdata")
	public void fourthsecondyamlCheck(JsonData data) {
		logger.info(data.getValue("ContentType"));
		logger.info(data.getValue("user"));
		logger.info(data.getValue("check"));

	}

}
