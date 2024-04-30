package com.example.teamcity_testproject.api.requests.checked;

import com.example.teamcity_testproject.api.models.User;
import com.example.teamcity_testproject.api.spec.Specifications;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;

public class AuthRequest {

    private User user;

    public AuthRequest(User user) {
        this.user = user;
    }

    public String getCsfrToken() {
        return RestAssured
                .given()
                .spec(Specifications.getSpec().authSpec(user))
                .get("/authenticationTest.html?csrf")
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().asString();
    }
}
