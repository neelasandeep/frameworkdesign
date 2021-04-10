package baseFrameWork;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {
	WebDriver driver = null;

	public WebDriver createInstance(String browserName) {

		switch (browserName.toLowerCase()) {

		case "chrome":
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			//options.addArguments("headless");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
			break;
		case "firefox":
			FirefoxOptions foptions = new FirefoxOptions();
			foptions.addArguments("-private");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(foptions);
			break;

		case "ie":
			InternetExplorerOptions ieOptions = new InternetExplorerOptions();
			ieOptions.addCommandSwitches("-private");
			WebDriverManager.iedriver().driverVersion("10").setup();
			driver = new InternetExplorerDriver(ieOptions);
			break;
		case "edge":
			//EdgeOptions edgeOptions = new EdgeOptions(); 
			//edgeOptions.
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		default:
			ChromeOptions doptions = new ChromeOptions();
			doptions.addArguments("--incognito");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(doptions);
		}
		return driver;
	}

}
