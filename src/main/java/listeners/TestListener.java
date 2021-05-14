package listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseframework.DriverFactory;
import baseframework.ExtentFactory;
import baseframework.ExtentReportNG;

public class TestListener implements ITestListener {
	private static Logger logger = LogManager.getLogger();
	private static final String TEST = "Test Case: ";
	public static final ExtentReports report = ExtentReportNG.setupExtentReport();

	ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		// before each test case
		if (!result.getMethod().getMethodName().equals("feature")) {
			test = report.createTest(result.getMethod().getMethodName());
			ExtentFactory.getInstance().setExtent(test);
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		if (!result.getMethod().getMethodName().equals("feature")) {
			ExtentFactory.getInstance().getExtent().log(Status.PASS,
					TEST + result.getMethod().getMethodName() + " is Passed.");
			ExtentFactory.getInstance().removeExtentObject();
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {

		ExtentFactory.getInstance().getExtent().log(Status.FAIL,
				TEST + result.getMethod().getMethodName() + " is Failed.");
		ExtentFactory.getInstance().getExtent().log(Status.FAIL, result.getThrowable());

		// add screenshot for failed test.
		File src = ((TakesScreenshot) DriverFactory.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
		Date date = new Date();
		String actualDate = format.format(date);

		String screenshotPath = System.getProperty("user.dir") + "/Reports/Screenshots/" + actualDate + ".jpeg";
		File dest = new File(screenshotPath);

		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			logger.info(e);
		}
		try {
			ExtentFactory.getInstance().getExtent().addScreenCaptureFromPath(screenshotPath,
					"Test case failure screenshot");
			ExtentFactory.getInstance().removeExtentObject();

		} catch (IOException e) {
			logger.info(e);
		}

	}
	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentFactory.getInstance().getExtent().log(Status.SKIP,
				TEST + result.getMethod().getMethodName() + " is skipped.");
		ExtentFactory.getInstance().removeExtentObject();
	}
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		/* this is not used now */
	}
	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		/* this is not used now */
	}
	@Override
	public void onStart(ITestContext context) {
		/* this is not used now */
	}
	@Override
	public void onFinish(ITestContext context) {

		report.flush();
	}

}