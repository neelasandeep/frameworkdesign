package testbase;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import commoncomponents.PropertiesUtility;
import customExceptions.FrameWorkException;
import customExceptions.TestDataException;
import dataconstants.Constants;
import dataconstants.Pathconstants;
import testDataModel.JsonData;
import utilities.JsonTestData;

public abstract class BaseTest {

	BrowserFactory browserFactory = new BrowserFactory();

	public abstract String getTestDataParser();

	@BeforeMethod
	public void setup() {

		String browser = PropertiesUtility.getProperty(Constants.BROWSER);
		String url = PropertiesUtility.getProperty(Constants.URL);
		DriverFactory.getInstance().setDriver(browserFactory.createInstance(browser));
		WebDriver driver = DriverFactory.getInstance().getDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(url);

	}

	@BeforeClass(alwaysRun = true)
	@Parameters({ "testdata" })
	public void loadData(@Optional String inputdatafile) throws FrameWorkException {
		if (inputdatafile == null) {
			throw new FrameWorkException("testdata filename is null");
		} else {
			loadtestdata(inputdatafile);
		}
	}

	public void loadtestdata(String inputdatafile) {
		JsonTestData.setTestData(inputdatafile);
	}

	@AfterMethod
	public void tearDown() {
		DriverFactory.getInstance().closeBrowser();

	}

	@DataProvider(name = "testdata")
	public Object[][] testdata(Method testMethod) throws TestDataException {
		if (getTestDataParser().equals(Pathconstants.JSON) || getTestDataParser().equals(Pathconstants.YAML)) {

			JsonData data = JsonTestData.getdata(testMethod.getName());

			return data.getValue("TestDataType").equals(PropertiesUtility.getProperty(Constants.TESTINGTYPE))
					? new Object[][] { { data } }
					: new Object[0][0];

		} else {
			throw new TestDataException("invalid test data file");
		}

	}

}
