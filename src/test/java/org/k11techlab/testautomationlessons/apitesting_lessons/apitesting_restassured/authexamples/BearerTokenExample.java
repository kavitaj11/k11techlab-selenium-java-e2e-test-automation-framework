package org.k11techlab.testautomationlessons.apitesting_lessons.apitesting_restassured.authexamples;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BearerTokenExample {
    @Test
    public void testBearerToken() {
        // Set Base URI
        RestAssured.baseURI = "https://api.example.com/secure-endpoint";

        // Bearer Token
        String token = "your_bearer_token_here";

        // Send GET request with Bearer Token
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + token) // Add token to Authorization header
                .when()
                .get();

        // Assertions
        Assert.assertEquals(response.getStatusCode(), 200, "Unexpected Status Code!");
    }
}
