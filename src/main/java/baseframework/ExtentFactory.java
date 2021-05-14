package baseframework;

import com.aventstack.extentreports.ExtentTest;

public class ExtentFactory {
	// Singleton design Pattern
	// private constructor so that no one else can create object of this class
	private ExtentFactory() {

	}

	private static ExtentFactory instance = null;

	public static ExtentFactory getInstance() {
		if (instance == null) {
			instance = new ExtentFactory();
		}
		return instance;
	}

	// factory design pattern --> define separate factory methods for creating
	// objects and create objects by calling that methods
	ThreadLocal<ExtentTest> extent = new ThreadLocal<>();

	public ExtentTest getExtent() {
		return extent.get();
	}

	public void setExtent(ExtentTest extentTestObject) {
		extent.set(extentTestObject);
	}

	public void removeExtentObject() {
		extent.remove();
	}
}