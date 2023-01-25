package WorkflowApi;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.api.ApiUtil;

import static utils.api.ApiUtil.*;
import static utils.api.ApiUtil.GetPlaceRequestApi;

public class ValidateApiCalls {
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


}

