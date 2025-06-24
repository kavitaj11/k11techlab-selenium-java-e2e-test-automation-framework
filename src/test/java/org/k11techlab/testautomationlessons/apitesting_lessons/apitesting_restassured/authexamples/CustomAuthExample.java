package org.k11techlab.testautomationlessons.apitesting_lessons.apitesting_restassured.authexamples;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CustomAuthExample {
    @Test
    public void testCustomAuth() {
        // Set Base URI
        RestAssured.baseURI = "https://api.example.com";

        // API Key
        String apiKey = "your_api_key_here";

        // Send GET request with custom header
        Response response = RestAssured.given()
                .header("x-api-key", apiKey) // Custom header for API key
                .when()
                .get("/endpoint");

        // Assertions
        Assert.assertEquals(response.getStatusCode(), 200, "Unexpected Status Code!");
    }
}
