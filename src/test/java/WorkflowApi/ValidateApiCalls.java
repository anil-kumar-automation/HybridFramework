package WorkflowApi;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static utils.api.ApiUtil.*;
import static utils.api.ApiUtil.postApi;

public class ValidateApiCalls {
    static String placeId;
    /*public static void main(String[] args){*/

    @Test()
    public void validateAccessTokenActualRequestApiTest() {
        JsonPath jsonPath1 = new JsonPath(GetActualRequestApi());
        String url = jsonPath1.getString("url");
        System.out.println(url);
        Assert.assertEquals(url, "rahulshettycademy.com");
    }

    @Test()
    public void validatePlacesApiTest() {
        postAddPlaceRequestApi();
        PUTUpdatePlaceRequestApi();
        JsonPath js2 = new JsonPath(GetPlaceRequestApi());
        String actualAddress = js2.getString("address");
        System.out.println(actualAddress);
        Assert.assertEquals(actualAddress, "Pacific ocean");

    }

    @Test()
    public void validateAddPlacesApiWithPojoClass() {
        PostAddPlaceApiWithSerialize();
    }

    @Test()
    public void validateAccessTokenActualRequestApiWithPojoClass() {
        GetActualRequestApiUsingPojo();
    }


    @Test()
    public void GraphQlApiTest() {
        PostGraphQlTestWithQueryAndMutation();
    }

    @Test()
    public void SpecBuilderTest() {
        PostSpecBuilder();
    }

    @Test()
    public void PostParameterApi() {
        postApi("https://rahulshettyacademy.com", "{\n" +
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
                "}", "key", "qaclick123", "/maps/api/place/add/json", 200);

        JsonPath js = new JsonPath(responseString); //for parsing Json
        placeId = js.getString("place_id");
        System.out.println("placeId  is= " + placeId);
    }

    @Test()
    public void getParameterApi() {

        getApi("https://rahulshettyacademy.com", "place_id", placeId, "maps/api/place/get/json", 200);
    }


}

