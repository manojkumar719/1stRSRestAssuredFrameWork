package stepDefinitions;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@deletePlace")
	public void beforeDeletePlace() throws Exception {
		if(StepDefinitions.placeId == null)
		{
			StepDefinitions d = new StepDefinitions();
			d.add_place_payload_with("3rdHouse", "Tamil", "India");
			d.user_calls_with_request("AddPlaceAPI", "POST");
			d.verify_place_id_created_maps_with_using("3rdHouse", "GetPlaceAPI");
		}
	}

}
