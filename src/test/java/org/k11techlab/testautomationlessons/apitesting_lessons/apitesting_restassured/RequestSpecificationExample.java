package org.k11techlab.testautomationlessons.apitesting_lessons.apitesting_restassured;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RequestSpecificationExample {

    // Declare RequestSpecification object
    private static RequestSpecification requestSpec;

    // Setup RequestSpecification before running tests
    @BeforeClass
    public static void setupRequestSpec() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://jsonplaceholder.typicode.com") // Base URI
                .addHeader("Content-Type", "application/json")      // Common Header
                .addHeader("Accept", "application/json")           // Accept Header
                .build();
    }

    // Test case for a GET request
    @Test
    public void testGetRequest() {
        // Use the RequestSpecification
        Response response = RestAssured.given()
                .spec(requestSpec)  // Attach the specification
                .when()
                .get("/posts/1");  // Perform GET request

        // Assertions
        Assert.assertEquals(response.getStatusCode(), 200, "Unexpected status code!");
        Assert.assertTrue(response.getBody().asString().contains("userId"), "Response body missing 'userId'");
    }

    // Test case for a POST request
    @Test
    public void testPostRequest() {
        // Request body for POST
        String requestBody = "{\n" +
                "    \"title\": \"foo\",\n" +
                "    \"body\": \"bar\",\n" +
                "    \"userId\": 1\n" +
                "}";

        // Use the RequestSpecification
        Response response = RestAssured.given()
                .spec(requestSpec)  // Attach the specification
                .body(requestBody)  // Add request body
                .when()
                .post("/posts");    // Perform POST request

        // Assertions
        Assert.assertEquals(response.getStatusCode(), 201, "Unexpected status code!");
        Assert.assertTrue(response.getBody().asString().contains("\"id\""), "Response body missing 'id'");
    }

    // Test case for a DELETE request
    @Test
    public void testDeleteRequest() {
        Response response = RestAssured.given()
                .spec(requestSpec)  // Attach the specification
                .when()
                .delete("/posts/1"); // Perform DELETE request

        // Assertions
        Assert.assertEquals(response.getStatusCode(), 200, "Unexpected status code!");
    }
}
