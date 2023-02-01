package WorkflowApi;

import basicApi.Auth;
import basicApi.PlacesApi;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import listeners.ExtentReporter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.api.ValidatorOperation;
import utils.selenium.JsonRead;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static utils.selenium.Log.info1;


public class ApiValidateTest {

    Response response;
    String placeId;


    /**
     * reference API Doc: https://restful-booker.herokuapp.com/apidoc/index.html
     */


    @Test
    public void validLoginTest() throws FileNotFoundException {
        ExtentReporter.extentTest.get().info("Description: Valid Login Scenario with username and password.");
        Auth response = new Auth();
        Response loginTokenResponse = response.getLoginToken("admin", "password123");
        info1("loginTokenResponse is = " + loginTokenResponse.asString());


        try {
            //ExtentTestManager.getTest().log(LogStatus.INFO, "URL is: " +response.url)
            response.assertIt(200);
            ExtentReporter.extentTest.get().log(Status.PASS, "Description: Valid Login Scenario with username and password.");
            ExtentReporter.extentTest.get().info("Asserting response code");


            response.assertIt("token", null, ValidatorOperation.NOT_EMPTY);
            ExtentReporter.extentTest.get().info("Asserting response value not empty case");
            response.assertIt("token", null, ValidatorOperation.NOT_NULL);
            ExtentReporter.extentTest.get().info("Asserting response value not null case");

        } catch (AssertionError e) {
            ExtentReporter.extentTest.get().log(Status.FAIL, "Assertion Failure: " + e.getMessage());

        }

    }

    @Test
    public void invalidLoginTest() throws FileNotFoundException {
        ExtentReporter.extentTest.get().info("Description: InValid Login Scenario with username and password.");
        Auth response = new Auth();
        Response loginTokenResponse = response.getLoginToken("admibgvn", "pasnxbnxsword123");
        ExtentReporter.extentTest.get().log(Status.FAIL,"InvalidLoginTokenResponse is = " + loginTokenResponse.asString());

        try {
            //ExtentTestManager.getTest().log(LogStatus.INFO, "URL is: " +response.url);
            response.assertIt(200);
            ExtentReporter.extentTest.get().info("Asserting response code");


            response.assertIt("reason", "Bad credentials", ValidatorOperation.EQUALS);
            ExtentReporter.extentTest.get().info("Asserting response value == Bad credentials");


        } catch (AssertionError e) {
            ExtentReporter.extentTest.get().log(Status.FAIL, "Assertion Failure: " + e.getMessage());
        }

    }

    @Test(priority = 1)
    public void AddPlaceApiTest() throws IOException, ParseException {
        PlacesApi placesApi = new PlacesApi();
        response = placesApi.AddPlaceApi();
        placesApi.assertIt(200);
        Object obj = new JSONParser().parse(response.asString());
        JSONObject jsonObject = (JSONObject) obj;

        placeId = JsonRead.getValue(jsonObject, "place_id");
        info1("addPlace response := " + response.asString());
        info1("addPlace response of  placeId value   := " + placeId);


    }

    @Test(priority = 2)
    public void UpdatePlaceApiTest() throws IOException {
        PlacesApi placesApi = new PlacesApi();
        response= placesApi.UpdatePlaceApi(placeId);
        info1("UpdatePlace response is =" + response.asString());
    }

    @Test(priority = 3)
    public void GetPlaceApiTest() throws IOException {
        PlacesApi placesApi = new PlacesApi();
        response = placesApi.GetPlaceApi(placeId);
        info1("GetPlace response is =" + response.asString());
        String address = placesApi.getJson_Path(response, "address");
        info1("GetPlace response of address value is =" + address);
        Assert.assertEquals(address, "Summer Walk, Africa");
    }

    @Test(priority = 4)
    public void DeletePlaceApiTest() throws IOException {
        PlacesApi placesApi = new PlacesApi();
        response = placesApi.DeletePlaceApi(placeId);
        info1("UpdatePlace response is =" + response.asString());
    }


    @Test
    public void AddPlaceApiWithPojoTest() throws IOException {
        PlacesApi placesApi = new PlacesApi();
        response = placesApi.AddPlaceApiWithSerialize();
        info1("AddPlace pojo approach response is = " + response.asString());
    }
}
