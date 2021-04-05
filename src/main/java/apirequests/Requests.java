package apirequests;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
public class Requests {
	static Response response;
	public static  Response getRequest(String endpoint) {
		return  given().when().get(endpoint);
	}
	public static Response postRequest(String endpoint,String body) {
		return given().body(body).when().post(endpoint);
	}

}
