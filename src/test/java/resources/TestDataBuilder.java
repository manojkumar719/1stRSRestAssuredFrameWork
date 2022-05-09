package resources;

import java.util.Arrays;

import pojo.AddPlace;
import pojo.LocationLatLng;

public class TestDataBuilder {

public AddPlace add_Place_Payload(String name, String language, String address) {
	
		AddPlace ap = new AddPlace();
		
		LocationLatLng l=new LocationLatLng();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		
		ap.setLocation(l);
		ap.setAccuracy(50);
		ap.setName(name);
		ap.setAddress(address);
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setTypes(Arrays.asList("shoe park","shop"));
		ap.setWebsite("http://MyCountry.com");
		ap.setLanguage(language);
	
		return ap;
		
	}

public String delete_Place_Payload(String placeId) {
	return "{\"place_id\":\""+placeId+"\"}";
}
}
