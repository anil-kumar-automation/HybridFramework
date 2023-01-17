package utils.api;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class ApiUtil {
    public static void main(String[] args) {


        // validate if Add Place API is workimg as expected
        //Add place-> Update Place with New Address -> Get Place to validate if New address is present in response

        //given - all input details
        //when - Submit the API -resource,http method
        //Then - validate the response

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        //Add Place
        String AddPlaceResponse = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"location\": {\n" +
                        "    \"lat\": -38.383494,\n" +
                        "    \"lng\": 33.427362\n" +
                        "  },\n" +
                        "  \"accuracy\": 50,\n" +
                        "  \"name\": \"Frontline house\",\n" +
                        "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                        "  \"address\": \"29, side layout, cohen 09\",\n" +
                        "  \"types\": [\n" +
                        "    \"shoe park\",\n" +
                        "    \"shop\"\n" +
                        "  ],\n" +
                        "  \"website\": \"http://google.com\",\n" +
                        "  \"language\": \"French-IN\"\n" +
                        "}")
                .when().post("maps/api/place/add/json")
                .then().statusCode(200).extract().response().asString();

        System.out.println("ADDResponse = " + AddPlaceResponse);
        JsonPath js = new JsonPath(AddPlaceResponse); //for parsing Json
        String placeId = js.getString("place_id");
        System.out.println(placeId);


        //Update Place
        String newAddress = "Summer Walk, Africa";
        String updatePlaceResponse= given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
                .body("{\r\n" +
                        "\"place_id\":\""+placeId+"\",\r\n" +
                        "\"address\":\""+newAddress+"\",\r\n" +
                        "\"key\":\"qaclick123\"\r\n" +
                        "}").
                        when().put("maps/api/place/update/json")
                .then().log().all().statusCode(200).extract().response().asString();
        System.out.println("updatePlaceResponse = " + updatePlaceResponse);



        //Get Place
        String getPlaceResponse=	given().log().all().queryParam("key", "qaclick123")
                .queryParam("place_id",placeId)
                .when().get("maps/api/place/get/json")
                .then().log().all().statusCode(200).extract().response().asString();

        System.out.println("getPlaceResponse =" + getPlaceResponse);
        JsonPath js1 = new JsonPath(getPlaceResponse);
        String actualAddress =js1.getString("address");
        System.out.println(actualAddress);
        Assert.assertEquals(actualAddress, "Pacific ocean");

    }
}