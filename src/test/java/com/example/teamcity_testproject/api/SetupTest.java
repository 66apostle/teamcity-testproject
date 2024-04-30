package com.example.teamcity_testproject.api;

import com.example.teamcity_testproject.api.config.Config;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

import static io.restassured.RestAssured.given;

public class SetupTest extends BaseTest {

    private static final String AUTH_SETTINGS_ENDPOINT = "/app/rest/server/authSettings";

    @BeforeSuite
    public void AuthSettings () {
        RestAssured.baseURI = Config.getProperty("host") + AUTH_SETTINGS_ENDPOINT;
        given().contentType("application/json")
                .body("{\"perProjectPermissions\": true}")
                .when()
                .put()
                .then()
                .statusCode(200);

    }
}
