package apitestrunner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/apifeature", glue = { "stepdefs" }

)

public class Runner extends AbstractTestNGCucumberTests {

	static String scenarioName;

	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}

}
