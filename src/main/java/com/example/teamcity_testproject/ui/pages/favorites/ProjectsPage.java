package com.example.teamcity_testproject.ui.pages.favorites;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.example.teamcity_testproject.ui.Selectors;
import com.example.teamcity_testproject.ui.elements.ProjectElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.elements;

public class ProjectsPage extends FavoritesPage {

    private final static String FAVORITE_PROJECTS_URL = "/favorite/projects";

    private ElementsCollection subprojects = elements(Selectors.byClass("Subprojects__limitWidth--MY"));

    public ProjectsPage open() {
        Selenide.open(FAVORITE_PROJECTS_URL);
        waitUntilFavoritePageIsLoaded();
        return this;
    }

    public List<ProjectElement> getSubprojects() {
        return generatePageElements(subprojects, ProjectElement::new);
    }

}
