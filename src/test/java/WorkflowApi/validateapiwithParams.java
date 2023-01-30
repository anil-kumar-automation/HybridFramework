package WorkflowApi;

import basicApi.PlacesApi;
import org.testng.annotations.Test;
import utils.api.API;

import java.io.FileNotFoundException;


public class validateapiwithParams extends API {

    @Test
    public void ParamApi() throws FileNotFoundException {
        PlacesApi response = new PlacesApi();
        response.AddPlaceApi();
        System.out.println(" AddPlace response = " + response);
    }
}
