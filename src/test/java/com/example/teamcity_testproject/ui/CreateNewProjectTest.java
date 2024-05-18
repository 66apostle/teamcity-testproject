package com.example.teamcity_testproject.ui;

import com.codeborne.selenide.Condition;
import com.example.teamcity_testproject.ui.pages.favorites.ProjectsPage;
import com.example.teamcity_testproject.ui.pages.admin.CreateNewProject;
import org.testng.annotations.Test;

public class CreateNewProjectTest extends BaseUiTest {

    @Test
    public void authorizedUserShouldBeAbleCreateNewProject() {

        var testData = testDataStorage.addTestData();
        var url = "https://github.com/66apostle/demoqa-tests";

        loginAsUser(testData.getUser());

        new CreateNewProject()
                .open(testData.getProject().getParentProject().getLocator())
                .createProjectByUrl(url)
                .setupProject(testData.getProject().getName(), testData.getBuildType().getName());

        new ProjectsPage().open().getSubprojects().stream().reduce((first, second) -> second).get().getHeader()
                .shouldHave(Condition.text(testData.getProject().getName()));
    }

}
