package org.k11techlab.testautomationlessons.apitesting_lessons.apitesting_restassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ApiValidationTest {

    // Set the base URI for the API
    static {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    /**
     * Test method to validate the API's status code, response time, content type,
     * and categorize HTTP status codes.
     */
    @Test(dataProvider = "endpointsDataProvider")
    public void validateApi(String endpoint, int expectedStatusCode, long maxResponseTime, String expectedContentType) {
        System.out.println("\nTesting endpoint: " + endpoint);

        // Send GET request to the endpoint
        Response response = given()
                                .log().uri() // Log the requested URI
                            .when()
                                .get(endpoint)
                            .then()
                                .log().ifError() // Log response details if an error occurs
                                .extract().response();

        // Response Data
        int actualStatusCode = response.getStatusCode();
        long actualResponseTime = response.getTime();
        String actualContentType = response.getHeader("Content-Type");

        // Assertions
        // 1. Validate HTTP Status Code
        Assert.assertEquals(actualStatusCode, expectedStatusCode, "Status code mismatch!");

        // 2. Validate Response Time
        Assert.assertTrue(actualResponseTime <= maxResponseTime,
                "Response time exceeded: " + actualResponseTime + "ms > " + maxResponseTime + "ms");

        // 3. Validate Content-Type Header
        Assert.assertTrue(actualContentType.contains(expectedContentType),
                "Expected content type not found! Got: " + actualContentType);

        // 4. Categorize and Validate Status Codes
        if (actualStatusCode >= 100 && actualStatusCode < 200) {
            System.out.println("Informational Response: " + actualStatusCode);
        } else if (actualStatusCode >= 200 && actualStatusCode < 300) {
            System.out.println("Success Response: " + actualStatusCode);
        } else if (actualStatusCode >= 300 && actualStatusCode < 400) {
            System.out.println("Redirection Response: " + actualStatusCode);
        } else if (actualStatusCode >= 400 && actualStatusCode < 500) {
            System.out.println("Client Error Response: " + actualStatusCode);
        } else if (actualStatusCode >= 500) {
            System.out.println("Server Error Response: " + actualStatusCode);
        } else {
            Assert.fail("Unexpected status code: " + actualStatusCode);
        }
    }

    /**
     * DataProvider for multiple API endpoints.
     */
    @DataProvider(name = "endpointsDataProvider")
    public Object[][] endpointsDataProvider() {
        return new Object[][]{
            // {endpoint, expectedStatusCode, maxResponseTime (ms), expectedContentType}
            {"/posts", 200, 2000, "application/json"},
            {"/comments", 200, 2000, "application/json"},
            {"/users", 200, 2000, "application/json"},
            {"/invalid-endpoint", 404, 1000, "application/json"},
            {"/todos", 200, 1500, "application/json"}
        };
    }
}
