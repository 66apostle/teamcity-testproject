package com.example.teamcity_testproject.api.requests;

import com.example.teamcity_testproject.api.requests.checked.CheckedBuildConfig;
import com.example.teamcity_testproject.api.requests.checked.CheckedProject;
import com.example.teamcity_testproject.api.requests.checked.CheckedUser;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;

@Getter

public class CheckedRequests {
    private CheckedUser userRequest;

    private CheckedProject projectRequest;

    private CheckedBuildConfig buildConfigRequest;

    public CheckedRequests(RequestSpecification spec) {
        this.userRequest = new CheckedUser(spec);
        this.projectRequest = new CheckedProject(spec);
        this.buildConfigRequest = new CheckedBuildConfig(spec);
    }
}
