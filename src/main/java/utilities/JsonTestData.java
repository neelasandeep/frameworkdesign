package utilities;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import dataconstants.Pathconstants;
import testDataModel.JsonData;

public class JsonTestData {
	private static JsonData[] testdata;
	static JsonData data = null;

	public static void setTestData(String inputDataFile) {
		ObjectMapper mapper;
		FilePathBuilder filePathBuilder = new FilePathBuilder(inputDataFile);
		filePathBuilder.setParentDirectory(Pathconstants.TESTDATA_DIRECTORY);
		try {
			if (inputDataFile.contains(Pathconstants.JSON)) {
				mapper = new ObjectMapper();
				System.out.println(filePathBuilder.getFilePath());
				testdata = mapper.readValue(new File(filePathBuilder.getFilePath()), JsonData[].class);

			} else if (inputDataFile.contains(Pathconstants.YAML)) {
				mapper = new ObjectMapper(new YAMLFactory());
				testdata = mapper.readValue(new File(filePathBuilder.getFilePath()), JsonData[].class);

//				InputStream targetStream = new FileInputStream(new File(filePathBuilder.getFilePath()));
//				Object obj = new Yaml().load(targetStream);
//				testdata = mapper.readValue(mapper.writeValueAsString(obj), JsonData[].class);

			}
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static synchronized JsonData getdata(String testCaseName) {

		Arrays.asList(testdata).forEach(jsondata -> {

			if (jsondata.getTestCaseName().equals(testCaseName)) {
				data = jsondata;
			}
		});
		return data;

	}

}
