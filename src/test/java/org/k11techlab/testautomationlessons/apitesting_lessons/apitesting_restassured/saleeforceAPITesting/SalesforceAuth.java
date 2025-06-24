package org.k11techlab.testautomationlessons.apitesting_lessons.apitesting_restassured.saleeforceAPITesting;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class SalesforceAuth {
    public static String getAccessToken() {
        String tokenUrl = "https://<your_instance>.salesforce.com/services/oauth2/token";

        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "password");
        params.put("client_id", "<Consumer Key>");
        params.put("client_secret", "<Consumer Secret>");
        params.put("username", "<Salesforce Username>");
        params.put("password", "<Salesforce Password + Security Token>");

        Response response = RestAssured
                .given()
                .contentType("application/x-www-form-urlencoded")
                .formParams(params)
                .post(tokenUrl);

        String accessToken = response.jsonPath().getString("access_token");
        System.out.println("Access Token: " + accessToken);
        return accessToken;
    }
}

