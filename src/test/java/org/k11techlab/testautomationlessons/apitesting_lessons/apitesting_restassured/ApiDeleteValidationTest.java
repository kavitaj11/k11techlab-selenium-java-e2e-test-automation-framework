package org.k11techlab.testautomationlessons.apitesting_lessons.apitesting_restassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ApiDeleteValidationTest {

    // Set the base URI for the API
    static {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    /**
     * Test method to validate the DELETE request's status code, response time, content type,
     * and response body.
     */
    @Test(dataProvider = "deleteEndpointsDataProvider")
    public void validateDeleteApi(String endpoint, int expectedStatusCode, 
                                  long maxResponseTime, String expectedContentType) {
        System.out.println("\nTesting DELETE endpoint: " + endpoint);

        // Send DELETE request to the endpoint
        Response response = given()
                                .log().uri()  // Log the URI
                            .when()
                                .delete(endpoint)
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

        // 3. Validate Content-Type Header (Optional)
        if (expectedContentType != null) {
            Assert.assertTrue(actualContentType.contains(expectedContentType),
                    "Expected content type not found! Got: " + actualContentType);
        }

        // Categorize HTTP Status Codes (Optional)
        categorizeStatusCode(actualStatusCode);
    }

    /**
     * Categorize and log the HTTP status code.
     */
    private void categorizeStatusCode(int statusCode) {
        if (statusCode >= 100 && statusCode < 200) {
            System.out.println("Informational Response: " + statusCode);
        } else if (statusCode >= 200 && statusCode < 300) {
            System.out.println("Success Response: " + statusCode);
        } else if (statusCode >= 300 && statusCode < 400) {
            System.out.println("Redirection Response: " + statusCode);
        } else if (statusCode >= 400 && statusCode < 500) {
            System.out.println("Client Error Response: " + statusCode);
        } else if (statusCode >= 500) {
            System.out.println("Server Error Response: " + statusCode);
        } else {
            Assert.fail("Unexpected status code: " + statusCode);
        }
    }

    /**
     * DataProvider for multiple DELETE API endpoints.
     */
    @DataProvider(name = "deleteEndpointsDataProvider")
    public Object[][] deleteEndpointsDataProvider() {
        return new Object[][]{
            // {endpoint, expectedStatusCode, maxResponseTime (ms), expectedContentType}
            {
                "/posts/1",
                200,
                2000,
                "application/json"
            },
            {
                "/posts/2",
                200,
                2000,
                "application/json"
            },
            {
                "/posts/3",
                200,
                2000,
                "application/json"
            }
        };
    }
}
