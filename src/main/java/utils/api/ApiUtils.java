package utils.api;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class ApiUtils {

    static RequestSpecification reqSpec;
    HttpOperation method;
    String url;
    Response resp;


    public void init(String url, HttpOperation method, String ResourceName) throws FileNotFoundException {
        this.url = url;
        this.method = method;
        reqSpec = given();
        if (ResourceName.equalsIgnoreCase("auth")) {
            authApiLogs();
        } else if (ResourceName.equalsIgnoreCase("addPlace")) {
            AddPlaceApiLogs();
        }
        else if (ResourceName.equalsIgnoreCase("updatePlace")) {
            UpdatePlaceApiLogs();
        }
        else if (ResourceName.equalsIgnoreCase("getPlace")) {
            getPlaceApiLogs();
        }
        else if (ResourceName.equalsIgnoreCase("DeletePlace")) {
            DeletePlaceApiLogs();
        }
    }

    public void authApiLogs() throws FileNotFoundException {

        PrintStream log = new PrintStream(new FileOutputStream("authApiInfo.txt")); //this is help to print logs to mentioned file
        reqSpec = reqSpec.filter(RequestLoggingFilter.logRequestTo(log)).filter(ResponseLoggingFilter.logResponseTo(log));
    }

    public void AddPlaceApiLogs() throws FileNotFoundException {

        PrintStream log = new PrintStream(new FileOutputStream("addPlaceApiInfo.txt")); //this is help to print logs to mentioned file
        reqSpec = reqSpec.filter(RequestLoggingFilter.logRequestTo(log)).filter(ResponseLoggingFilter.logResponseTo(log));
    }

    public void UpdatePlaceApiLogs() throws FileNotFoundException {

        PrintStream log = new PrintStream(new FileOutputStream("updatePlaceApiInfo.txt")); //this is help to print logs to mentioned file
        reqSpec = reqSpec.filter(RequestLoggingFilter.logRequestTo(log)).filter(ResponseLoggingFilter.logResponseTo(log));
    }

    public void getPlaceApiLogs() throws FileNotFoundException {

        PrintStream log = new PrintStream(new FileOutputStream("GetPlaceApiInfo.txt")); //this is help to print logs to mentioned file
        reqSpec = reqSpec.filter(RequestLoggingFilter.logRequestTo(log)).filter(ResponseLoggingFilter.logResponseTo(log));
    }

    public void DeletePlaceApiLogs() throws FileNotFoundException {

        PrintStream log = new PrintStream(new FileOutputStream("DeletePlaceApiInfo.txt")); //this is help to print logs to mentioned file
        reqSpec = reqSpec.filter(RequestLoggingFilter.logRequestTo(log)).filter(ResponseLoggingFilter.logResponseTo(log));
    }

    public void initBase(String baseConst) {
        Helper getHelp = new Helper();
        getHelp.set_path("src/main/resources/Constants.properties");
        try {
            RestAssured.baseURI = getHelp.loadProperties(baseConst);
        } catch (Exception e) {
            e.printStackTrace();
        }
        reqSpec = RestAssured.given();
    }

    public String getJson_Path(Response res, String key) {
        String json_response = res.asString();
        JsonPath js = new JsonPath(json_response);
        String value = js.getString(key);
        return value;
    }

    public String GenerateStringFromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    public void setHeader(String[][] head) {
        for (int row = 0; row < head.length; row++)
            for (int col = 0; col < head[row].length; col++)
                reqSpec.header(head[row][col], head[row][col + 1]);
    }

    public void setHeader(String head, String val) {
        reqSpec.header(head, val);
    }

    public void setBody(String body) {
        reqSpec.body(body);
    }

    public void setBodySerialize(Object body) {
        reqSpec.body(body);
    }

    public void setFormParam(String key, String val) {
        reqSpec.formParam(key, val);
    }

    public void setQueryParam(String key, String val) {
        reqSpec.queryParam(key, val);
    }

    public Response callIt() {
        if (method.toString().equalsIgnoreCase("get")) {
            resp = reqSpec.get(url);
            return resp;
        } else if (method.toString().equalsIgnoreCase("post")) {
            resp = reqSpec.post(url);
            return resp;
        } else if (method.toString().equalsIgnoreCase("patch")) {
            resp = reqSpec.patch(url);
            return resp;
        } else if (method.toString().equalsIgnoreCase("put")) {
            resp = reqSpec.put(url);
            return resp;
        } else if (method.toString().equalsIgnoreCase("delete")) {
            resp = reqSpec.delete(url);
            return resp;
        }
        return null;
    }

    public ApiUtils assertIt(String key, Object val, ValidatorOperation operation) {

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

    public ApiUtils assertIt(int code) {

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

    public ApiUtils assertIt(int code, int optionalCode) {
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
