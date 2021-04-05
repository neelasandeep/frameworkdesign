package testDataManagement;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;

import constants.ApplicationConstants;
import constants.Dataconstants;
import models.testDataModel.JsonData;
import utilities.FilePathBuilder;
import utilities.PropertiesUtility;

public class JsonTestData implements TestData {
	private static JsonData[] testdata;
	static JsonData data = null;

	public  void setTestData(String inputDataFile) {
		ObjectMapper mapper;
		FilePathBuilder filePathBuilder = new FilePathBuilder(inputDataFile);
		filePathBuilder.setParentDirectory(Dataconstants.TESTDATA_DIRECTORY);
		try {

			mapper = new ObjectMapper();
			System.out.println(filePathBuilder.getFilePath());
			testdata = mapper.readValue(new File(filePathBuilder.getFilePath()), JsonData[].class);

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public  synchronized Object[][] getdata(String testCaseName) {

		Arrays.asList(testdata).forEach(jsondata -> {

			if (jsondata.getTestCaseName().equals(testCaseName)) {
				data = jsondata;
			}
		});
		return data.getValue("TestDataType").equals(PropertiesUtility.getProperty(ApplicationConstants.TESTINGTYPE))
				? new Object[][] { { data } }
				: new Object[0][0];

	}
	public  synchronized static JsonData getJsondata(String testCaseName) {

		Arrays.asList(testdata).forEach(jsondata -> {

			if (jsondata.getTestCaseName().equals(testCaseName)) {
				data = jsondata;
			}
		});
		return data;

	}

}
