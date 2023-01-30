package utils.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class BuildSpec extends API {
    public static RequestSpecification req;

    /**
     * Build   -Request Spec Builder:
     *
     * @return
     */
    public static RequestSpecification RequestSpec() throws FileNotFoundException {
        if (req == null) {
            PrintStream stream = new PrintStream(new FileOutputStream("loggingForPlaceAPI.txt"));
            req = new RequestSpecBuilder().setRelaxedHTTPSValidation().setBaseUri(initBase("baseUrl"))

                    .addFilter(RequestLoggingFilter.logRequestTo(stream))
                    .addFilter(ResponseLoggingFilter.logResponseTo(stream))
                    .setContentType(ContentType.JSON).build();
            return req;
        }
        return req;
    }



        /** Build Response Spec Builder:*/
        ResponseSpecification responsespec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

    }
