package com.example.teamcity_testproject.api.requests.checked;

import com.example.teamcity_testproject.api.models.Project;
import com.example.teamcity_testproject.api.requests.Request;
import io.restassured.specification.RequestSpecification;
import com.example.teamcity_testproject.api.requests.Crudinterface;
import com.example.teamcity_testproject.api.requests.unchecked.UncheckedProject;
import org.apache.http.HttpStatus;

public class CheckedProject extends Request implements Crudinterface {

    public CheckedProject(RequestSpecification spec) {
        super(spec);
    }

    @Override
    public Project create(Object obj) {
        return new UncheckedProject(spec).create(obj)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().as(Project.class);
    }

    @Override
    public Project get(String id) {
        return new UncheckedProject(spec)
                .get(id)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().as(Project.class);
    }

    @Override
    public Object update(Object obj) {
        return null;
    }

    @Override
    public Object delete(String id) {
        return new UncheckedProject(spec)
                .delete(id)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().asString();
    }
}