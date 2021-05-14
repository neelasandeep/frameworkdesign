package testdatamanagement;

import constants.Dataconstants;
import customexceptions.TestDataException;

public class TestDataResource {
	 static  TestData testData;
	 private TestDataResource() {}

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
