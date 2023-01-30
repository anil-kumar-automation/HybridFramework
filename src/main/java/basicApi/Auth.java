/*
package basicApi;

import utils.api.API;
import utils.api.HttpOperation;

import static io.restassured.RestAssured.given;

public class Auth  extends API {

    public Auth(){}

    */
/**
     * Creates a new auth token to use for access to the PUT and DELETE /booking
     *
     * *//*


    private static Object code= "4%2F0AWgavdcng9OmbRt9qybvJBjJN0twxM87kepjw9Sm0BgYFByLJqqeQSaBH1n-xCwgyF8Gvw";

    private void createToken(String userName, String passWord) {
        initBase("Host");
        init("/auth", HttpOperation.POST);
        setHeader("Content-Type","application/json");
        setBody("{ \"username\" : \""+userName+"\", \"password\" : \""+passWord+"\"}");
    }


    */
/**
     * @param	userName (Username string value for the restful bokker application)
     * 			passWord (password string value for the restful bokker application)
     *
     * @return 	returns login token
     * *//*

    public String getLoginToken(String userName, String passWord) {
        createToken(userName, passWord);
        String response = callIt();
        //System.out.println(response);
        return response;
    }

    public static String generateAccessToken() {

        //Get Access Token using Oauth2
        String generateAccessTokenBody =
                given().urlEncodingEnabled(false)
                        .queryParams("code", code)
                        .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                        .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                        .queryParams("grant_type", "authorization_code")
                        //  .queryParams("state", "verifyfjdss")
                        // .queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")
                        // .queryParam("scope", "email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email")
                        .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
                        .when()
                        .post("https://www.googleapis.com/oauth2/v4/token").asString();
        System.out.println("generateAccessTokenBody = " +generateAccessTokenBody);
        return generateAccessTokenBody;
    }
}
*/
