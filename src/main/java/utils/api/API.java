package utils.api;


import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;


public class API {


    RequestSpecification reqSpec;
    HttpOperation method;
    String url;
    static Response resp;

    public void init(String url, HttpOperation method) {
        this.url = url;
        this.method = method;
        reqSpec = given();
    }

    public void initbase(String url, HttpOperation method) {
        if (method.toString().equalsIgnoreCase("get")) {
            resp = reqSpec.when().get(url);
        } else if (method.toString().equalsIgnoreCase("post")) {
            resp = reqSpec.when().post(url);
        } else if (method.toString().equalsIgnoreCase("patch")) {
            resp =reqSpec.when().patch(url);
        } else if (method.toString().equalsIgnoreCase("put")) {
            resp = reqSpec.when().put(url);
        } else if (method.toString().equalsIgnoreCase("delete")) {
            resp = reqSpec.when().delete(url);
        }
    }


    public static String initBase(String baseConst) {
        Helper getHelp = new Helper();
        getHelp.set_path("src/main/resources/Constants.properties");
       /* try {
           // RestAssured.baseURI = getHelp.loadProperties(baseConst);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return getHelp.loadProperties(baseConst);
    }


    public void setHeader(String head, String val) throws FileNotFoundException {
        reqSpec = given().spec(BuildSpec.RequestSpec()).header(head, val);
    }

    public void setBody(String body) throws FileNotFoundException {
        reqSpec = given().spec(BuildSpec.RequestSpec()).body(body);
    }

    public void setFormParam(String key, String val) throws FileNotFoundException {
        reqSpec = given().spec(BuildSpec.RequestSpec()).formParam(key, val);
    }

    public void setQueryParam(String key, String val) throws FileNotFoundException {
        reqSpec = given().spec(BuildSpec.RequestSpec()).queryParam(key, val);
    }

    public String callItMethod() {
        if (method.toString().equalsIgnoreCase("get")) {
            resp = when().get(url);
            return resp.asString();
        } else if (method.toString().equalsIgnoreCase("post")) {
            resp = when().post(url);
            return resp.asString();
        } else if (method.toString().equalsIgnoreCase("patch")) {
            resp = when().patch(url);
            return resp.asString();
        } else if (method.toString().equalsIgnoreCase("put")) {
            resp = when().put(url);
            return resp.asString();
        } else if (method.toString().equalsIgnoreCase("delete")) {
            resp = when().delete(url);
            return resp.asString();
        }
        return "invalid method set for API";
    }

    public API assertIt(String key, Object val, ValidatorOperation operation) {

        switch (operation.toString()) {
            case "EQUALS":
                resp.then().body(key, equalTo(val));
                break;

            case "KEY_PRESENTS":
                resp.then().body(key, hasKey(key));
                break;

            case "HAS_ALL":
                break;

            case "NOT_EQUALS":
                resp.then().body(key, not(equalTo(val)));
                break;

            case "EMPTY":
                resp.then().body(key, empty());
                break;

            case "NOT_EMPTY":
                resp.then().body(key, not(emptyArray()));
                break;

            case "NOT_NULL":
                resp.then().body(key, notNullValue());
                break;

            case "HAS_STRING":
                resp.then().body(key, containsString((String) val));
                break;

            case "SIZE":
                resp.then().body(key, hasSize((int) val));
                break;
        }

        return this;
    }


    public API assertItCode(int code) {

        resp.then().statusCode(code);

        return this;
    }

    public String extractString(String path) {
        return resp.jsonPath().getString(path);
    }

    public int extractInt(String path) {
        return resp.jsonPath().getInt(path);
    }

    public List<?> extractList(String path) {
        return resp.jsonPath().getList(path);
    }

    public Object extractIt(String path) {
        return resp.jsonPath().get(path);
    }

    public String extractHeader(String header_name) {
        return resp.header(header_name);
    }

    public String getResponseString() {
        return resp.asString();
    }

    public void fileUpload(String key, String path, String mime) {
        reqSpec.multiPart(key, new File(path), mime);
    }

    public void multiPartString(String key, String input) {
        reqSpec.multiPart(key, input);
    }

    public void printResp() {
        resp.print();
    }

    public String getCookieValue(String cookieName) {
        return resp.getCookie(cookieName);
    }

    public API assertIt(int code, int optionalCode) {
        resp.then().statusCode(anyOf(equalTo(code), equalTo(optionalCode)));
        return this;
    }

    public void setRedirection(boolean followRedirects) {
        reqSpec.when().redirects().follow(followRedirects);
    }

    public void ListResponseHeaders() {
        // Get all the headers. Return value is of type Headers.
        Headers allHeaders = resp.headers();
        // Iterate over all the Headers
        for (Header header : allHeaders) {
            System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
        }
    }

    public int getStatusCode() {
        return resp.getStatusCode();
    }

    public Headers getAllHeaders() {
        return resp.getHeaders();
    }
}


