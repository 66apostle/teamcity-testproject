package com.example.teamcity_testproject.api;

import com.example.teamcity_testproject.api.generators.TestDataStorage;
import com.example.teamcity_testproject.api.requests.CheckedRequests;
import com.example.teamcity_testproject.api.requests.UncheckedRequests;
import com.example.teamcity_testproject.api.spec.Specifications;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected SoftAssertions softy;

    public TestDataStorage testDataStorage;

    public CheckedRequests checkedWithSuperUser = new CheckedRequests(Specifications.getSpec().superUserSpec());

    public UncheckedRequests uncheckedWithSuperUser = new UncheckedRequests(Specifications.getSpec().superUserSpec());

    @BeforeMethod
    public void beforeTest() {
        softy = new SoftAssertions();
        testDataStorage = TestDataStorage.getStorage();
    }

    @AfterMethod
    public void afterTest() {
        testDataStorage.delete();
        softy.assertAll();
    }
}
