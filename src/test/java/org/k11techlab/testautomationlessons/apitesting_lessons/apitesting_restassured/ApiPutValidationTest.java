package org.k11techlab.testautomationlessons.apitesting_lessons.apitesting_restassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ApiPutValidationTest {

    // Set the base URI for the API
    static {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    /**
     * Test method to validate the PUT request's status code, response time, content type,
     * and response body.
     */
    @Test(dataProvider = "putEndpointsDataProvider")
    public void validatePutApi(String endpoint, String requestBody, int expectedStatusCode, 
                                long maxResponseTime, String expectedContentType) {
        System.out.println("\nTesting PUT endpoint: " + endpoint);

        // Send PUT request to the endpoint with a request body
        Response response = given()
                                .header("Content-Type", "application/json") // Set header
                                .body(requestBody)  // Set the request body
                                .log().uri()  // Log the URI
                            .when()
                                .put(endpoint)
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

        // 4. Validate Response Body (optional)
        if (expectedStatusCode == 200) {
            String responseBody = response.getBody().asString();
            Assert.assertTrue(responseBody.contains("id"), "Response body does not contain expected 'id'");
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
     * DataProvider for multiple PUT API endpoints and their request bodies.
     */
    @DataProvider(name = "putEndpointsDataProvider")
    public Object[][] putEndpointsDataProvider() {
        return new Object[][]{
            // {endpoint, requestBody, expectedStatusCode, maxResponseTime (ms), expectedContentType}
            {
                "/posts/1",
                "{\"id\":1,\"title\":\"foo updated\",\"body\":\"bar updated\",\"userId\":1}",
                200,
                2000,
                "application/json"
            },
            {
                "/posts/2",
                "{\"id\":2,\"title\":\"Hello Updated\",\"body\":\"World Updated\",\"userId\":2}",
                200,
                2000,
                "application/json"
            },
            {
                "/posts/3",
                "{\"id\":3,\"title\":\"Post 3 Updated\",\"body\":\"Content Updated\",\"userId\":3}",
                200,
                2000,
                "application/json"
            }
        };
    }
}
