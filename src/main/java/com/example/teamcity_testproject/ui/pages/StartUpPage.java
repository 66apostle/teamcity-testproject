package com.example.teamcity_testproject.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.example.teamcity_testproject.ui.Selectors;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.element;

public class StartUpPage extends Page{

    private SelenideElement proceedButton = element(Selectors.byId("proceedButton"));

    private SelenideElement submitButton = element(Selectors.byClass("btn btn_primary submitButton "));

    private SelenideElement acceptLicense = element(Selectors.byId("accept"));

    private SelenideElement restoreFromBackupButton = element("input[]");

    private SelenideElement BackFileUploaded = element("input[]");

    public StartUpPage open() {
        Selenide.open("/");
        return this;
    }

    public StartUpPage getHeader() {
        Selenide.element("header");
        return this;
    }

    public StartUpPage setupTeamCityServer() {
        waitUntilPageIsLoaded();
        proceedButton.click();
        waitUntilPageIsLoaded();
        proceedButton.click();
        waitUntilPageIsLoaded();
        $("body").scrollTo();
        acceptLicense.click();
        acceptLicense.shouldBe(Condition.enabled, Duration.ofMinutes(5));
        submitButton.click();
        return this;
    }
}
