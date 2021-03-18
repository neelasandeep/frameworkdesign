package commoncomponents;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesUtility {
	static Properties properties = new Properties();
	static Logger logger = Logger.getLogger(PropertiesUtility.class);

	public static String getProperty(String key) {
		File src = new File("./src/main/resources/Config.properties");
		try {
			FileInputStream fis = new FileInputStream(src);

			properties.load(fis);

		} catch (Exception e) {
			logger.warn("Unable to load config File", e);
		}
		return properties.getProperty(key);
	}

}
