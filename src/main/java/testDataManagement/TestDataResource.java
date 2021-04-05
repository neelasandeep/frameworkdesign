package testDataManagement;

import constants.Dataconstants;
import customExceptions.TestDataException;

public class TestDataResource {
	public static TestData testData;

	public static void setTestData(String inputFileName, String sheetName) {
		if (inputFileName.contains(Dataconstants.JSON)) {
			testData = new JsonTestData();
			testData.setTestData(inputFileName);

		} else if (inputFileName.contains(Dataconstants.YAML)) {
			testData = new YMLTestData();
			testData.setTestData(inputFileName);

		} else if (inputFileName.contains(Dataconstants.XLS)) {
			testData = new ExcelTestData(sheetName);
			testData.setTestData(inputFileName);
		}
	}

	public static Object[][] getdata(String testCaseName) throws TestDataException {
		return testData.getdata(testCaseName);
	}
	

}
