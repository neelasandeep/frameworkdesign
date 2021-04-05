package apiMethods;

import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import apirequests.Requests;
import constants.ApplicationConstants;
import dataBases.CommonDBOperation;
import io.restassured.response.Response;
import models.apimodel.User;
import models.testDataModel.JsonData;
import utilities.PropertiesUtility;

public class UserApi {
	Response response;

	public Response hitPostRequest(String endpoint, JsonData data) throws JsonProcessingException {
		CommonDBOperation dbcon = new CommonDBOperation();
		ArrayList<String> result = dbcon
				.getEmployeeDetailsfromDB(PropertiesUtility.getProperty(ApplicationConstants.APIQUERY));
		User user = new User();
		user.setUsername(result.get(0));
		user.setPassword(result.get(1));
		ObjectMapper mapper = new ObjectMapper();
		String body = mapper.writeValueAsString(user);
		return Requests.postRequest(endpoint, body);

	}

	public Response hitGetRequest(String endpoint, JsonData data) {

		return Requests.getRequest(endpoint);

	}

}
