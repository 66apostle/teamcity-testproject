package com.example.teamcity_testproject.ui.pages.admin;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.example.teamcity_testproject.ui.Selectors;
import com.example.teamcity_testproject.ui.pages.Page;

import static com.codeborne.selenide.Selenide.element;

public class CreateNewProject extends Page {

    private SelenideElement urlInput = element(Selectors.byId("url"));
    private SelenideElement projectNameInput = element(Selectors.byId("projectName"));
    private SelenideElement buildTypeNameInput = element(Selectors.byId("buildTypeName"));


    public CreateNewProject open(String parentProjectId) {
        Selenide.open("/admin/createObjectMenu.html?projectId="
                +  parentProjectId + "&showMode=createProjectMenu");
        waitUntilPageIsLoaded();
        return this;
    }

    public CreateNewProject createProjectByUrl(String url) {
        urlInput.sendKeys(url);
        submit();
        return this;
    }

    public void setupProject(String projectName, String buildTypeName) {
        projectNameInput.clear();
        projectNameInput.sendKeys(projectName);
        buildTypeNameInput.clear();
        buildTypeNameInput.sendKeys(buildTypeName);
        submit();

    }
}
