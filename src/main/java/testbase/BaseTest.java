package testbase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import commoncomponents.PropertiesUtility;
import dataconstants.Constants;

public class BaseTest {
	
	BrowserFactory browserFactory = new BrowserFactory();

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

	@AfterMethod
	public void tearDown() {
		DriverFactory.getInstance().closeBrowser();

	}

}
