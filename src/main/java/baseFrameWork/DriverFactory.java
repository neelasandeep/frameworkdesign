package baseFrameWork;

import java.io.Serializable;

import org.openqa.selenium.WebDriver;

public class DriverFactory implements Serializable {

	private static final long serialVersionUID = 1L;

	private DriverFactory() {
		if(instance!=null) {
			throw new RuntimeException("this is not vorrect way of creating Object");
		}
	}

	private static DriverFactory instance = new DriverFactory();

	public static DriverFactory getInstance() {

		return instance;
	}

	protected Object readResolve() {
		return instance;
	}

	ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	public void setDriver(WebDriver driverParam) {
		driver.set(driverParam);
	}

	public WebDriver getDriver() {
		return driver.get();
	}

	public void closeBrowser() {

		driver.get().quit();
		driver.remove();
	}

}
