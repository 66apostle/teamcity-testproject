package com.example.teamcity_testproject.api;

import com.example.teamcity_testproject.api.generators.TestDataStorage;
import com.example.teamcity_testproject.api.requests.CheckedRequests;
import com.example.teamcity_testproject.api.requests.UncheckedRequests;
import com.example.teamcity_testproject.api.spec.Specifications;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseApiTest extends BaseTest {

    public TestDataStorage testDataStorage;

    public CheckedRequests checkedWithSuperUser = new CheckedRequests(Specifications.getSpec().superUserSpec());

    public UncheckedRequests uncheckedWithSuperUser = new UncheckedRequests(Specifications.getSpec().superUserSpec());



    @BeforeMethod
    public void setupTest() {
        testDataStorage = TestDataStorage.getStorage();
    }

    @AfterMethod
    public void cleanTest() {
        testDataStorage.delete();
    }

}
