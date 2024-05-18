package com.example.teamcity_testproject.ui;

import com.codeborne.selenide.Configuration;
import com.example.teamcity_testproject.api.BaseTest;
import com.example.teamcity_testproject.api.config.Config;
import com.example.teamcity_testproject.api.models.User;
import com.example.teamcity_testproject.api.requests.checked.CheckedUser;
import com.example.teamcity_testproject.api.spec.Specifications;
import com.example.teamcity_testproject.ui.pages.LoginPage;
import org.testng.annotations.BeforeSuite;

public class BaseUiTest extends BaseTest {

    @BeforeSuite
    public void setupUiTests() {
        Configuration.browser = "firefox";
        Configuration.baseUrl = "http://" + Config.getProperty("host");
        Configuration.remote = Config.getProperty("remote");
        Configuration.reportsFolder = "target/surefire-reports";
        Configuration.downloadsFolder = "target/downloads";

        BrowserSettings.setup(Config.getProperty("browser"));
    }

    public void loginAsUser(User user) {
        new CheckedUser(Specifications.getSpec().superUserSpec()).create(user);
        new LoginPage().open().login(user);
    }
}
