package basicApi;


import io.restassured.response.Response;
import utils.api.ApiUtils;
import utils.api.HttpOperation;

import java.io.FileNotFoundException;


public class Auth extends ApiUtils {
	

	
    public Auth(){}
      
    /**
     * Creates a new auth token to use for access to the PUT and DELETE /booking
     * 
     * */
    private void createToken(String userName, String passWord) throws FileNotFoundException {
		initBase("HostBaseUrl");
		init("/auth", HttpOperation.POST,"login");
		setHeader("Content-Type","application/json");
		setBody("{ \"username\" : \""+userName+"\", \"password\" : \""+passWord+"\"}");

	}
	

	/**
	 * @param    userName (Username string value for the restful bokker application)
	 * 			passWord (password string value for the restful bokker application)
	 *
	 * @return 	returns login token
	 * */
	public Response getLoginToken(String userName, String passWord) throws FileNotFoundException {
		createToken(userName, passWord);
		Response responsen = callIt();
		return responsen;
	}

	
	
}
