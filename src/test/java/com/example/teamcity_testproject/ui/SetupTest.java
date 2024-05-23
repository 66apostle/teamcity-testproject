package com.example.teamcity_testproject.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.example.teamcity_testproject.ui.pages.StartUpPage;
import org.testng.annotations.Test;

public class SetupTest extends BaseUiTest{

    @Test
    public void startUpTest() {
        new StartUpPage()
                .open()
                .setupTeamCityServer();

        Selenide.element("header").shouldHave(Condition.text("Create Administrator Account"));
    }
}
