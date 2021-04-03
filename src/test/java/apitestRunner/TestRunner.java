package apitestRunner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

@CucumberOptions(features = "src/test/java/apifeature", glue = { "stepdefs" }

)

public class TestRunner  {

static String scenarioName;

//	@DataProvider(parallel = true)
//	public Object[][] features() {
//		return super.scenarios();
//	}

	private TestNGCucumberRunner testNGCucumberRunner;

	@BeforeClass(alwaysRun = true)
	public void setUpClass() {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	}

	@Test(description = "Runs Cucumber Feature",dataProvider = "features")
	public void feature(PickleEventWrapper eventWrapper,CucumberFeatureWrapper featureWrappers) throws Throwable {
		

		testNGCucumberRunner.runScenario(eventWrapper.getPickleEvent());
		;
	

	}
	
	

	@DataProvider(parallel = true,name="features")
	public Object[][] features() {
//		return testNGCucumberRunner.provideFeatures();
		return testNGCucumberRunner.provideScenarios();
	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass() {
		testNGCucumberRunner.finish();
	}

	

}
