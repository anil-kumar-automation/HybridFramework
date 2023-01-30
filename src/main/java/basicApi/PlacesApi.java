package basicApi;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojoClasses.AddPlace;
import pojoClasses.Location;
import utils.api.API;
import utils.api.APIResources;
import utils.api.BuildSpec;
import utils.api.HttpOperation;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PlacesApi extends API {

    public PlacesApi() {
    }

    static String placeId;

    public void AddPlaceApi() throws FileNotFoundException {
        BuildSpec.RequestSpec()

      /*  initBase("baseUrl");
        setQueryParam("key","qaclick123");
        setHeader("Content-Type", "application/json");
        setBody("{\\r\\n  \\\"location\\\": {\\r\\n    \\\"lat\\\": -38.383494,\\r\\n    \\\"lng\\\": 33.427362\\r\\n  },\\r\\n  \\\"accuracy\\\": 50,\\r\\n  \\\"name\\\": \\\"Frontline house\\\",\\r\\n  \\\"phone_number\\\": \\\"(+91) 983 893 3937\\\",\\r\\n  \\\"address\\\": \\\"29, side layout, cohen 09\\\",\\r\\n  \\\"types\\\": [\\r\\n    \\\"shoe park\\\",\\r\\n    \\\"shop\\\"\\r\\n  ],\\r\\n  \\\"website\\\": \\\"http:\\/\\/google.com\\\",\\r\\n  \\\"language\\\": \\\"French-IN\\\"\\r\\n}");
        initbase("/maps/api/place/add/json", HttpOperation.POST);
        assertItCode(200);
        getResponseString();*/
    }

    public static String postAddPlaceRequestApi() {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        APIResources resourceAPI = APIResources.valueOf("AddPlaceAPI");
        System.out.println("AddPlaceAPI = " + resourceAPI.getResource());
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
                /*.when().post("maps/api/place/add/json")*/
                .when().post(resourceAPI.getResource())
                .then().statusCode(200).extract().response().asString();

        System.out.println("ADDPlaceResponse = " + AddPlaceResponse);
        JsonPath js = new JsonPath(AddPlaceResponse); //for parsing Json
        placeId = js.getString("place_id");  //doubt :- how to extract string value in different method r class
        System.out.println(placeId);
        return AddPlaceResponse;
    }

    public static String PUTUpdatePlaceRequestApi() {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        //Update Place
        String newAddress = "Summer Walk, Africa";
        String updatePlaceResponse = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body("{\r\n" +
                        "\"place_id\":\"" + placeId + "\",\r\n" +
                        "\"address\":\"" + newAddress + "\",\r\n" +
                        "\"key\":\"qaclick123\"\r\n" +
                        "}").
                        when().put("maps/api/place/update/json")
                .then().log().all().statusCode(200).extract().response().asString();
        System.out.println("updatePlaceResponse = " + updatePlaceResponse);
        return updatePlaceResponse;
    }

    public static String GetPlaceRequestApi() {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        //Get Place
        String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
                .queryParam("place_id", placeId)
                .when().get("maps/api/place/get/json")
                .then().log().all().statusCode(200).extract().response().asString();

        System.out.println("getPlaceResponse =" + getPlaceResponse);
        return getPlaceResponse;
    }


    public static void PostSpecBuilder() {
        AddPlace p = new AddPlace();
        p.setAccuracy(50);
        p.setAddress("29, side layout, cohen 09");
        p.setLanguage("French-IN");
        p.setPhone_number("(+91) 983 893 3937");
        p.setWebsite("https://rahulshettyacademy.com");
        p.setName("Frontline house");
        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");

        p.setTypes(myList);
        Location l = new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);

        /** Build   -Request Spec Builder:*/
        RequestSpecification requestspec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON).build();

        /** Build Response Spec Builder:*/
        ResponseSpecification responsespec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();


        /** this frist approach for the getting response using spec builders and
         this Rewriting Test with Request and Response Spec Builder :*/
        String AddPlaceResponse = given().spec(requestspec).body(p).when().post("/maps/api/place/add/json").
                then().spec(responsespec).extract().response().asString();
        System.out.println("AddPlaceResponse using specBuilder = " + AddPlaceResponse);


    }
}
