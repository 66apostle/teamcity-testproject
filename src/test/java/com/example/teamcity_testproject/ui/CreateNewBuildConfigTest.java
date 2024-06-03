package com.example.teamcity_testproject.ui;

import com.example.teamcity_testproject.ui.pages.admin.CreateNewProject;
import com.example.teamcity_testproject.ui.pages.favorites.ProjectsPage;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class CreateNewBuildConfigTest extends BaseUiTest {

    @Test
    public void authorizedUserShouldBeAbleCreateBuildConfiguration() {


        var testData = testDataStorage.addTestData();
        var url = "https://github.com/66apostle/demoqa-tests";

        loginAsUser(testData.getUser());

        new CreateNewProject()
                .open(testData.getProject().getParentProject().getLocator())
                .createProjectByUrl(url)
                .setupProject(testData.getProject().getName(), testData.getBuildType().getName());

        new ProjectsPage().open().getSubprojects();
        var parentElement = $(byAttribute("data-project-id", testData.getProject().getParentProject().getLocator()));
        parentElement.$(".ring-icon-icon SvgIcon__icon--wZ").click();
        $(byText("New Configuration")).click();

        new CreateNewProject().createProjectByUrl(url).setupProject(testData.getProject().getName(), testData.getBuildType().getName());
    }
}
