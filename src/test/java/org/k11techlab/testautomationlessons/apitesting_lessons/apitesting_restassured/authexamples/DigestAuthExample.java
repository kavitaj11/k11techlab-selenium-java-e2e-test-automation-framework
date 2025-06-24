package org.k11techlab.testautomationlessons.apitesting_lessons.apitesting_restassured.authexamples;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DigestAuthExample {
    @Test
    public void testDigestAuth() {
        // Set Base URI
        RestAssured.baseURI = "https://httpbin.org/digest-auth/auth/user/passwd";

        // Send GET request with Digest Auth credentials
        Response response = RestAssured.given()
                .auth()
                .digest("user", "passwd") // Digest credentials
                .when()
                .get();

        // Assertions
        Assert.assertEquals(response.getStatusCode(), 200, "Unexpected Status Code!");
    }
}
