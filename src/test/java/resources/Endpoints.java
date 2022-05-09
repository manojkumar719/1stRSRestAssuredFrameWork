package resources;

public enum Endpoints {
	
	AddPlaceAPI("/maps/api/place/add/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	
	String endpoint;
	
	Endpoints(String value) {
		endpoint = value;
	}
	
	public String getEndpoint() {
		return endpoint;
	}

}
