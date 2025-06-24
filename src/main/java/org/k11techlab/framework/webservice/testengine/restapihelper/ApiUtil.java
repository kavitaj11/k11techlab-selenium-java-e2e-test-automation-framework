package org.k11techlab.framework.webservice.testengine.restapihelper;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;
import org.k11techlab.framework.selenium.webuitestbase.ApplicationProperties;

import static io.restassured.RestAssured.given;
import java.util.Map;


public class ApiUtil {

    static {
        RestAssured.baseURI =  ApplicationProperties.WEB_SERVICE_URI.getStringVal();

    }


    public static String generateEndpoint(String resource) {
        return "/" + resource;
    }


    public static RequestSpecification generateRequest() {
        return given().contentType("application/json");
    }


    public static RequestSpecification addHeaders(RequestSpecification request, Map<String, String> headers) {
        headers.forEach(request::header);
        return request;
    }


    public static RequestSpecification generateData(RequestSpecification request, String contentType, Object body) {
        if (contentType != null) {
            request.contentType(contentType);
        }
        if (body != null) {
            request.body(body);
        }
        return request;
    }


    public static Response sendRequest(RequestSpecification request, String method, String endpoint) {
        request = request.when();
        switch (method) {
            case "GET":
                return request.get(endpoint);
            case "POST":
                return request.post(endpoint);
            case "PUT":
                return request.put(endpoint);
            case "PATCH":
                return request.patch(endpoint);
            case "DELETE":
                return request.delete(endpoint);
            default:
                return null;
        }
    }


    public static Response sendRequest(RequestSpecification request, String method,   String endpoint, Object body) {
        request = request.when();
        switch (method) {
            case "GET":
                return request.get(endpoint);
            case "POST":
                return request.body(body).post(endpoint);
            case "PUT":
                return request.body(body).put(endpoint);
            case "PATCH":
                return request.body(body).patch(endpoint);
            case "DELETE":
                return request.delete(endpoint);
            default:
                return null;
        }
    }




    public static int getStatusCode(Response response) {
        return response.getStatusCode();
    }


    public static String getResponseBody(Response response) {
        return response.getBody().asString();
    }
}
