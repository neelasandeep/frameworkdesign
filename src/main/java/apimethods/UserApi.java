package apimethods;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import apirequests.Requests;
import constants.ApplicationConstants;
import databases.CommonDBOperation;
import io.restassured.response.Response;
import models.apimodel.User;
import models.testdatamodel.JsonData;
import utilities.PropertiesUtility;

public class UserApi {
	Response response;
	private static Logger logger = LogManager.getLogger();
	public Response hitPostRequest(String endpoint, JsonData data) throws JsonProcessingException {
		logger.info(data);
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
		logger.info(data);
		return Requests.getRequest(endpoint);

	}

}
