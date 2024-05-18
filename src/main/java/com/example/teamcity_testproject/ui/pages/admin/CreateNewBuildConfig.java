package com.example.teamcity_testproject.ui.pages.admin;


import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.example.teamcity_testproject.ui.Selectors;

import static com.codeborne.selenide.Selenide.element;

public class CreateNewBuildConfig extends CreateNewProject {

    private SelenideElement projectMenu = element(Selectors.byClass("ring-icon-icon SvgIcon__icon--wZ"));

    public CreateNewBuildConfig open(String parentBuildProjectId) {
        Selenide.open("/admin/createObjectMenu.html?projectId="
                +  parentBuildProjectId + "&showMode=createBuildTypeMenu");
        waitUntilPageIsLoaded();
        return this;
    }

    public void openProjectMenu () {
        waitUntilPageIsLoaded();
        projectMenu.click();
    }

}
