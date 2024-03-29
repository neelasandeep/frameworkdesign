package baseframework;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import constants.ApplicationConstants;
import constants.Reportconsts;
import utilities.PropertiesUtility;

public class ExtentReportNG {

	protected static ExtentReports extent;
    private ExtentReportNG() {}
	public static ExtentReports setupExtentReport()  {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
		Date date = new Date();
		String actualDate = format.format(date);
		String dateFolder = new SimpleDateFormat("dd-MM-yyy").format(date);

		String reportPath = System.getProperty("user.dir") + "/Reports/" + dateFolder + "/"
				+ PropertiesUtility.getReportProperties(Reportconsts.TESTNAME) + actualDate + ".html";

		ExtentSparkReporter sparkReport = new ExtentSparkReporter(reportPath);

		extent = new ExtentReports();
		extent.attachReporter(sparkReport);

		sparkReport.config().setDocumentTitle("TestReport");
		sparkReport.config().setTheme(Theme.DARK);
		sparkReport.config().setReportName(Reportconsts.TESTNAME + "-REPORT");

		extent.setSystemInfo("Executed on Environment: ", PropertiesUtility.getProperty(ApplicationConstants.URL));
		extent.setSystemInfo("Executed on Browser: ", PropertiesUtility.getProperty(ApplicationConstants.BROWSER));
		extent.setSystemInfo("Executed on OS: ", System.getProperty("os.name"));
		extent.setSystemInfo("Executed by User: ", System.getProperty("user.name"));
		
		return extent;
	}

}