package basicApi;

import io.restassured.response.Response;
import pojoClasses.AddPlace;
import pojoClasses.Location;
import utils.api.APIResources;
import utils.api.ApiUtils;
import utils.api.HttpOperation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PlacesApi extends ApiUtils {


    public PlacesApi() {
    }

    //AddPlace
    public Response AddPlaceApi() throws IOException {
        APIResources AddPlaceResource = APIResources.valueOf("AddPlaceAPI");
        System.out.println("AddPlace end point api = " + AddPlaceResource.getResource());
        initBase("baseUrl");
        init(AddPlaceResource.getResource(), HttpOperation.POST, "addPlace");
        //init("/maps/api/place/add/json", HttpOperation.POST,"print");
        setQueryParam("key", "qaclick123");
        setHeader("Content-Type", "application/json");
        setBody(GenerateStringFromResource(System.getProperty("user.dir") + "/src/test/resources/payloads/AddPlace.json"));
        Response response = callIt();
        return response;
    }

    //UpdatePlace
    public Response UpdatePlaceApi(String placeId) throws IOException {
        String newAddress = "Summer Walk, Africa";
        APIResources UpdatePlaceResource = APIResources.valueOf("updatePlaceAPI");
        System.out.println("UpdatePlace end point api = " + UpdatePlaceResource.getResource());
        initBase("baseUrl");
        init(UpdatePlaceResource.getResource(), HttpOperation.PUT, "updatePlace");
        setQueryParam("key", "qaclick123");
        setHeader("Content-Type", "application/json");
        setBody("{\n" +
                "\"place_id\":\"" + placeId + "\",\n" +
                "\"address\":\"" + newAddress + "\",\n" +
                "\"key\":\"qaclick123\"\n" +
                "}");
        Response response = callIt();
        return response;


    }

    //GetPlace
    public Response GetPlaceApi(String placeId) throws IOException {
        APIResources GetPlaceResource = APIResources.valueOf("getPlaceAPI");
        System.out.println("GetPlace end point api = " + GetPlaceResource.getResource());
        initBase("baseUrl");
        init(GetPlaceResource.getResource(), HttpOperation.GET, "GetPlace");
        setQueryParam("key", "qaclick123");
        setQueryParam("place_id", placeId);
        setHeader("Content-Type", "application/json");
        Response response = callIt();
        return response;

    }

    //DeletePlace
    public Response DeletePlaceApi(String placeId) throws IOException {
        APIResources UpdatePlaceResource = APIResources.valueOf("deletePlaceAPI");
        System.out.println("GetPlace end point api = " + UpdatePlaceResource.getResource());
        initBase("baseUrl");
        init(UpdatePlaceResource.getResource(), HttpOperation.DELETE, "deletePlace");
        setQueryParam("key", "qaclick123");
        setHeader("Content-Type", "application/json");
        setBody("{\n" +
                "    \"place_id\":\"" + placeId + "\"\n" +
                "}");
        Response response = callIt();
        return response;

    }

    public Response AddPlaceApiWithSerialize() throws FileNotFoundException {
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

        APIResources AddPlaceResource = APIResources.valueOf("AddPlaceAPI");
        System.out.println("AddPlace end point api = " + AddPlaceResource.getResource());
        initBase("baseUrl");
        init(AddPlaceResource.getResource(), HttpOperation.POST, "print");
        setQueryParam("key", "qaclick123");
        setHeader("Content-Type", "application/json");
        setBodySerialize(p);
        Response response = callIt();
        return response;

    }

}