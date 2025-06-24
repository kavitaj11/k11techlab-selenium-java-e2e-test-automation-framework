package org.k11techlab.testautomationlessons.apitesting_lessons.apitesting_restassured.authexamples;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BasicAuthExample {
    @Test
    public void testBasicAuth() {
        // Set Base URI
        RestAssured.baseURI = "https://postman-echo.com/basic-auth";

        // Send GET request with Basic Auth credentials
        Response response = RestAssured.given()
                .auth()
                .preemptive()
                .basic("postman", "password") // Username and password
                .when()
                .get();

        // Assertions
        Assert.assertEquals(response.getStatusCode(), 200, "Unexpected Status Code!");
        Assert.assertTrue(response.jsonPath().getBoolean("authenticated"), "Authentication failed!");
    }
}
