package org.k11techlab.testautomationlessons.apitesting_lessons.apitesting_restassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ApiPostValidationTest {

    // Set the base URI for the API
    static {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    /**
     * Test method to validate the POST request's status code, response time, content type,
     * and response body.
     */
    @Test(dataProvider = "postEndpointsDataProvider")
    public void validatePostApi(String endpoint, String requestBody, int expectedStatusCode, 
                                 long maxResponseTime, String expectedContentType) {
        System.out.println("\nTesting POST endpoint: " + endpoint);

        // Send POST request to the endpoint with a request body
        Response response = given()
                                .header("Content-Type", "application/json") // Set header
                                .body(requestBody)  // Set the request body
                                .log().uri()  // Log the URI
                            .when()
                                .post(endpoint)
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
        if (expectedStatusCode == 201) {
            String responseBody = response.getBody().asString();
            Assert.assertTrue(responseBody.contains("id"), "Response body does not contain expected 'id'");
        }

        // Categorize HTTP Status Codes (Optional)
        validateStatusCode(actualStatusCode);
    }

    /**
     * Categorize and log the HTTP status code.
     */
    private void validateStatusCode(int statusCode) {
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
     * DataProvider for multiple POST API endpoints and their request bodies.
     */
    @DataProvider(name = "postEndpointsDataProvider")
    public Object[][] postEndpointsDataProvider() {
        return new Object[][]{
            // {endpoint, requestBody, expectedStatusCode, maxResponseTime (ms), expectedContentType}
            {
                "/posts",
                "{\"title\":\"foo\",\"body\":\"bar\",\"userId\":1}",
                201,
                2000,
                "application/json"
            },
            {
                "/posts",
                "{\"title\":\"Hello\",\"body\":\"World\",\"userId\":2}",
                201,
                2000,
                "application/json"
            },
            {
                "/comments",
                "{\"postId\":1, \"name\":\"comment1\", \"email\":\"test@example.com\", \"body\":\"Great post!\"}",
                201,
                2000,
                "application/json"
            }
        };
    }
}
