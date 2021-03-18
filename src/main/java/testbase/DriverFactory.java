package testbase;

import java.io.Serializable;

import org.openqa.selenium.WebDriver;

public class DriverFactory implements Serializable {

	private static final long serialVersionUID = 1L;

	private DriverFactory() {
	}

	private static DriverFactory instance = null;

	public static DriverFactory getInstance() {
		if (instance == null) {
			synchronized (DriverFactory.class) {
				instance = new DriverFactory();
			}
		}
		return instance;
	}
	protected Object readResolve() {
		return instance;
	}
	ThreadLocal<WebDriver> driver= new ThreadLocal<WebDriver>();
	public void setDriver(WebDriver driverParam) {
		driver.set(driverParam);
	}
	public WebDriver getDriver() {
		return driver.get();
	}
	
	public void closeBrowser() {
		driver.get().close();
		driver.get().quit();
		driver.remove();
	}
	
}
