package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.runner.RunWith;

import io.cucumber.java.en.*;
import io.cucumber.junit.Cucumber;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.LocationLatLng;
import resources.Endpoints;
import resources.TestDataBuilder;
import resources.Utils;




@RunWith(Cucumber.class)
public class StepDefinitions extends Utils{

	RequestSpecification req;
	Response res;
	TestDataBuilder tData = new TestDataBuilder();
	static String placeId;

	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws Exception {
		req = given().spec(reqSpecification()).body(tData.add_Place_Payload(name,language,address));
	}

	@When("User calls {string} with {string} request")
	public void user_calls_with_request(String endpoint, String httpMethod) {
		if(httpMethod.equalsIgnoreCase("post"))
			res = req.when().post(Endpoints.valueOf(endpoint).getEndpoint());
		else if(httpMethod.equalsIgnoreCase("get"))
			res = req.when().get(getEndPoint(endpoint));
		System.out.println(Endpoints.valueOf(endpoint).getEndpoint());
	}
		
	@Then("Verify the status code is {int}")
	public void verify_the_status_code_is(Integer int1) {
	    Assert.assertTrue("Status code Matched", res.getStatusCode() == int1);
	}
	
	@Then("Verify {string} in response body has value {string}")
	public void verify_in_response_body_has_value(String key, String expectedVal) {
	    Assert.assertEquals(getJsonPathValue(res, key), expectedVal);
	}
	
	@Then("Verify place_id created maps with {string} using {string}")
	public void verify_place_id_created_maps_with_using(String key, String endPoint) throws Exception {
		placeId = getJsonPathValue(res, "place_id");
	    req = given().spec(reqSpecification()).queryParam("place_id",placeId);
	    user_calls_with_request(endPoint,"GET");
	    Assert.assertEquals( getJsonPathValue(res, "name"), key);
	    
	}

	@Given("Delete place payload")
	public void delete_place_payload() throws Exception {
		req = given().spec(reqSpecification()).body(tData.delete_Place_Payload(placeId));
		
	}
	
	@Given("printing value in console")
	public void printing_value_in_console() {
		System.out.println("Hello, this is a demo scenario for checking tags");
	}





}
