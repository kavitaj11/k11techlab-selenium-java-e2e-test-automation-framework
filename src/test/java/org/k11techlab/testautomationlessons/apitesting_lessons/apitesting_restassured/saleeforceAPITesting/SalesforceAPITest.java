package org.k11techlab.testautomationlessons.apitesting_lessons.apitesting_restassured.saleeforceAPITesting;

// Query Accounts
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SalesforceAPITest {
    public static void main(String[] args) {
        String accessToken = SalesforceAuth.getAccessToken();
        String instanceUrl = "<your_instance_url>"; // e.g., https://yourInstance.salesforce.com

        // Query Accounts
        String queryUrl = instanceUrl + "/services/data/v56.0/query?q=SELECT+Id,Name+FROM+Account";

        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + accessToken)
                .get(queryUrl);

        System.out.println("Response Code: " + response.statusCode());
        //System.out.println("Response Body: " + response.asPrettyString());
    }
}

