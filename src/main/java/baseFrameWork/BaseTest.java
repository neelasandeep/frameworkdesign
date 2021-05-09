package baseFrameWork;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import constants.ApplicationConstants;
import constants.Dataconstants;
import customExceptions.FrameWorkException;
import customExceptions.TestDataException;
import testDataManagement.TestDataResource;
import utilities.PropertiesUtility;

public abstract class BaseTest {

	BrowserFactory browserFactory = new BrowserFactory();
	protected WebDriver driver;
	public ConcurrentHashMap<Integer,WebDriver> drivers= new ConcurrentHashMap<>();

	public abstract String getTestDataParser();

	@BeforeMethod
	public void setup() {

		String browser = PropertiesUtility.getProperty(ApplicationConstants.BROWSER);
		//String url = PropertiesUtility.getProperty(ApplicationConstants.URL);
		String url=System.getProperty("url");
		DriverFactory.getInstance().setDriver(browserFactory.createInstance(browser));
		WebDriver driver = DriverFactory.getInstance().getDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(url);
		
		

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
