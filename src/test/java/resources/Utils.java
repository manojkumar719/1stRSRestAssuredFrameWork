package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	public static RequestSpecification req;
	public static ResponseSpecification res;
	
	public RequestSpecification reqSpecification() throws Exception {
		if(req == null)
		{
			PrintStream log = new PrintStream(new FileOutputStream("logs.txt"));
			req = new RequestSpecBuilder().setBaseUri(getGlobalVal("BASE_URL")).addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON).addQueryParam("key", "qaclick123").build();
			return req;
		}
		return req;
	}
	
	public ResponseSpecification resSpecification() {
		res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		return res;
	}
	
	public String getGlobalVal(String val) throws Exception {
		Properties p = new Properties();
		p.load(new FileInputStream("C:\\Users\\Manojg\\eclipse-workspace\\RSCucumber\\src\\test\\java\\resources\\config.properties"));
		return p.getProperty(val);
	}
	
	public String getJsonPathValue(Response resp, String key) {
		JsonPath jp = new JsonPath(resp.asString());
		return jp.get(key).toString();
	}
	
	public String getEndPoint(String endpoint) {
		return Endpoints.valueOf(endpoint).getEndpoint();
		
	}

}
