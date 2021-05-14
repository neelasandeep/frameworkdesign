package automationscripts;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import baseframework.BaseTest;
import constants.ApplicationConstants;
import databases.CommonDBOperation;
import models.testdatamodel.JsonData;
import utilities.PropertiesUtility;

public class Testcase2 extends BaseTest {
	private static Logger logger = LogManager.getLogger();

	@Override
	public String getTestDataParser() {

		return "json";
	}

	@Test(dataProvider = "testdata" )
	public void checkJsonData(JsonData data) {
		logger.info(data.getValue("ContentType"));
		logger.info(data.getValue("user"));

	}
	@Test(dataProvider = "testdata")
	public void checkJsonData2(JsonData data) {
		logger.info(data.getValue("ContentType"));
		logger.info(data.getValue("user"));

	}
	@Test(dataProvider = "testdata")
	public void checkJsonData3(JsonData data) {
		logger.info(data.getValue("ContentType"));
		logger.info(data.getValue("user"));

	}
	@Test(dataProvider = "testdata")
	public void checkJsonData4(JsonData data) {
		logger.info(data.getValue("ContentType"));
		logger.info(data.getValue("user"));

	}
	

	

	// @Test
	public void dabcheck() throws SQLException {

		CommonDBOperation dbcon = new CommonDBOperation();
		ArrayList<String> result = dbcon.getEmployeeDetailsfromDB(PropertiesUtility.getProperty(ApplicationConstants.DBQUERY));
		System.out.println(result);

	}

}
