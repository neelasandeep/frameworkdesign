package commoncomponents;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;




public class PropertiesUtility {
	static Properties properties = new Properties();
	private static  Logger logger = LogManager.getLogger();

	public static String getProperty(String key) {
		File src = new File("./src/test/resources/Config.properties");
		try {
			FileInputStream fis = new FileInputStream(src);

			properties.load(fis);
			logger.info("properties file reading is done");

		} catch (Exception e) {
			logger.info("Unable to load config File", e);
		}
		return properties.getProperty(key);
	}

	public static String getReportProperties(String key) {
		File src = new File("./src/test/resources/Report.properties");
		try {
			FileInputStream fis = new FileInputStream(src);

			properties.load(fis);
			logger.info("report properties file reading is done");

		} catch (Exception e) {
			logger.info("Unable to load config File", e);
		}
		return properties.getProperty(key);
	}

}
