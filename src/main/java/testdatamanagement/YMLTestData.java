package testdatamanagement;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import constants.ApplicationConstants;
import constants.Dataconstants;
import models.testdatamodel.JsonData;
import utilities.FilePathBuilder;
import utilities.PropertiesUtility;

public class YMLTestData implements TestData {
	private static JsonData[] testdata;
	private static Logger logger = LogManager.getLogger();
	static JsonData data = null;

	public void setTestData(String inputDataFile) {
		ObjectMapper mapper;
		FilePathBuilder filePathBuilder = new FilePathBuilder(inputDataFile);
		filePathBuilder.setParentDirectory(Dataconstants.TESTDATA_DIRECTORY);
		try {

			mapper = new ObjectMapper(new YAMLFactory());
			testdata = mapper.readValue(new File(filePathBuilder.getFilePath()), JsonData[].class);

//			InputStream targetStream = new FileInputStream(new File(filePathBuilder.getFilePath()));
//			Object obj = new Yaml().load(targetStream);
//			testdata = mapper.readValue(mapper.writeValueAsString(obj), JsonData[].class);

		} catch (IOException e) {

			logger.info(e);
		}
	}

	public synchronized Object[][] getdata(String testCaseName) {

		Arrays.asList(testdata).forEach(jsondata -> {

			if (jsondata.getTestCaseName().equals(testCaseName)) {
				data = jsondata;
			}
		});
		return data.getValue("TestDataType").equals(PropertiesUtility.getProperty(ApplicationConstants.TESTINGTYPE))
				? new Object[][] { { data } }
				: new Object[0][0];

	}

}
