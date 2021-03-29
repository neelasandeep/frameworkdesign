package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertiesUtility {
	static Properties properties = new Properties();
	private static Logger logger = LogManager.getLogger();

	public static String getProperty(String key) {
		File src = new File("./src/test/resources/Config.properties");
		try {
			loadSourceFile(src);

		} catch (Exception e) {
			logger.info("Unable to load config File", e);
		}
		return properties.getProperty(key);
	}

	public static String getReportProperties(String key) {
		File src = new File("./src/test/resources/Report.properties");
		try {
			loadSourceFile(src);

		} catch (Exception e) {
			logger.info("Unable to load config File", e);
		}
		return properties.getProperty(key);
	}

	private static void loadSourceFile(File src) throws FileNotFoundException, IOException {
		FileInputStream fis = new FileInputStream(src);

		properties.load(fis);
		logger.info("report properties file reading is done");
	}

}
