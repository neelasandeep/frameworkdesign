package baseframework;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import constants.ApplicationConstants;
import constants.Dataconstants;
import customexceptions.FrameWorkException;
import customexceptions.TestDataException;
import testdatamanagement.TestDataResource;
import utilities.PropertiesUtility;

public abstract class BaseTest {

	BrowserFactory browserFactory = new BrowserFactory();
	protected WebDriver driver;
	public ConcurrentHashMap<Integer,WebDriver> drivers= new ConcurrentHashMap<>();

	public abstract String getTestDataParser();
	private static Logger logger = LogManager.getLogger();
	@BeforeMethod
	public void setup() {

		String browser = PropertiesUtility.getProperty(ApplicationConstants.BROWSER);
		String Test = PropertiesUtility.getProperty(ApplicationConstants.FIREFOX);
		logger.info("calling firefox",Test);
		String browser1 = PropertiesUtility.getProperty(ApplicationConstants.SAFARI);
		String dbname = PropertiesUtility.getProperty(ApplicationConstants.DBNAME2);
		String dbaddress = PropertiesUtility.getProperty(ApplicationConstants.DBADDRESS);
		System.out.println(browser1);
		System.out.println(dbname);
		System.out.println(dbaddress);
		String env=System.getProperty("Environment");
		String url=getEnvUrl(env);
		DriverFactory.getInstance().setDriver(browserFactory.createInstance(browser));
		WebDriver localdriver = DriverFactory.getInstance().getDriver();
		localdriver.manage().window().maximize();
		localdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		localdriver.get(url);
		
		

	}
	public String getEnvUrl(String environment) {
		String url = null;
		try {
			if(environment.equals("DEV01")) {
				url= PropertiesUtility.getProperty(ApplicationConstants.DEV01);
			}else if(environment.equals("DEV02")) {
				url= PropertiesUtility.getProperty(ApplicationConstants.DEV02);
			}else if(environment.equals("QA02")) {
				url= PropertiesUtility.getProperty(ApplicationConstants.QA02);
			}else if(environment.equals("PPE01")) {
				url= PropertiesUtility.getProperty(ApplicationConstants.PPE01);
			}
		}catch(NullPointerException e) {
			logger.info("no URL present in the properties file");
		}
		
		return url;
	}

	@BeforeClass(alwaysRun = true)
	@Parameters({ "testdata", "sheetname" })
	public void loadData(@Optional String inputdatafile, @Optional String sheetname) throws FrameWorkException {
		if (inputdatafile == null) {
			throw new FrameWorkException("testdata filename is null");
		} else {

			loadtestdata(inputdatafile, sheetname);
		}
	}

	public void loadtestdata(String inputdatafile, String sheetName) {
		TestDataResource.setTestData(inputdatafile, sheetName);
	}

	@AfterMethod
	public void tearDown() {
		
		DriverFactory.getInstance().closeBrowser();
		

	}

	@DataProvider(name = "testdata")
	public Object[][] testdata(Method testMethod) throws TestDataException {
		if (getTestDataParser().equals(Dataconstants.JSON) || getTestDataParser().equals(Dataconstants.YAML)
				|| getTestDataParser().equals(Dataconstants.XLSX)) {

			return TestDataResource.getdata(testMethod.getName());

		} else {
			throw new TestDataException("invalid test data file");
		}

	}

}
