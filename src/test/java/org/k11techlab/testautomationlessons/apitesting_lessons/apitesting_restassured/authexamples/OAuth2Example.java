package org.k11techlab.testautomationlessons.apitesting_lessons.apitesting_restassured.authexamples;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OAuth2Example {
    @Test
    public void testOAuth2() {
        // Step 1: Get the OAuth 2.0 Token
        Response tokenResponse = RestAssured.given()
                .formParam("client_id", "your_client_id")
                .formParam("client_secret", "your_client_secret")
                .formParam("grant_type", "client_credentials")
                .post("https://auth.example.com/oauth/token");

        // Extract Access Token
        String accessToken = tokenResponse.jsonPath().getString("access_token");

        // Step 2: Use the Access Token to make an API request
        Response apiResponse = RestAssured.given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("https://api.example.com/secure-data");

        // Assertions
        Assert.assertEquals(apiResponse.getStatusCode(), 200, "Unexpected Status Code!");
    }
}
