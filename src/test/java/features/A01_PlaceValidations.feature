Feature: Validating Place API's

@addPlace
Scenario Outline: Verify if place is successfully added using AddPlaceAPI
	Given Add place payload with "<name>" "<language>" "<address>"
	When User calls "AddPlaceAPI" with "POST" request
	Then Verify the status code is 200
	And Verify "status" in response body has value "OK"
	And Verify "scope" in response body has value "APP"
	And Verify place_id created maps with "<name>" using "GetPlaceAPI"
Examples:
	|name       |language |address |
	|1stHouse|Telugu	|India		|
	|2ndHouse|Kannada	|India		|
	
@deletePlace
Scenario: Verification of delete place functionality
	Given Delete place payload
	When User calls "DeletePlaceAPI" with "POST" request
	Then Verify the status code is 200
	And Verify "status" in response body has value "OK"
	
@demoTags
Scenario: Trying multiple tags
	Given printing value in console