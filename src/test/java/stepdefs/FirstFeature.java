package stepdefs;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import baseFrameWork.ExtentFactory;
import constants.ApplicationConstants;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dataBases.CommonDBOperation;
import io.restassured.response.Response;
import listeners.TestListener;
import models.apimodel.User;
import utilities.PropertiesUtility;

public class FirstFeature {
	ExtentTest logger;
	ExtentTest test;
	Response response;
	String endpoint;
	String scenario;

	
	@Given("user had {string} to validate {string}")
	public void user_had_to_validate(String string, String string2) {
	   endpoint=string;
	   scenario=string2;
	   test = TestListener.report.createTest(string2);
		ExtentFactory.getInstance().setExtent(test);
		
	}

	@Given("when the user hits the reuest {string} tovalidate {string}")
	public void when_the_user_hits_the_reuest_tovalidate(String string, String string2) {
		endpoint=string;
		 test = TestListener.report.createTest(string2);
			ExtentFactory.getInstance().setExtent(test);
	}
	

	@When("method GET")
	public void method_get() {
		System.out.println("Test Case get with Thread Id:- "
				+ Thread.currentThread().getId());
		
		response = given().when().get(endpoint);
		
		System.out.println(response.asPrettyString());
		
	}

	@Then("status {int}")
	public void status(int int1) {
		Assert.assertEquals(response.getStatusCode(), int1);
	}

	

	@When("user hits endpoint Post request")
	public void user_hits_endpoint_post_request() throws JsonProcessingException {
		System.out.println("Test Case post with Thread Id:- "
				+ Thread.currentThread().getId());
		CommonDBOperation dbcon = new CommonDBOperation();
		ArrayList<String> result = dbcon
				.getEmployeeDetailsfromDB(PropertiesUtility.getProperty(ApplicationConstants.APIQUERY));
		User user = new User();
		user.setUsername(result.get(0));
		user.setPassword(result.get(1));
		ObjectMapper mapper = new ObjectMapper();
		String body=mapper.writeValueAsString(user);
		response=given().body(body).when().post(endpoint);
		System.out.println(response.asPrettyString());
		

	}

}
