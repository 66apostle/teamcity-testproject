package com.example.teamcity_testproject.api.generators;

import com.example.teamcity_testproject.api.models.BuildType;
import com.example.teamcity_testproject.api.models.NewProjectDescription;
import com.example.teamcity_testproject.api.models.User;
import com.example.teamcity_testproject.api.requests.unchecked.UncheckedProject;
import com.example.teamcity_testproject.api.requests.unchecked.UncheckedUser;
import com.example.teamcity_testproject.api.spec.Specifications;
import lombok.Builder;
import lombok.Data;

@Builder
@Data

public class TestData {

    private User user;

    private NewProjectDescription project;

    private BuildType buildType;

    public void delete() {

        var spec = Specifications.getSpec().authSpec(user);

        new UncheckedProject(spec).delete(project.getId());

        new UncheckedUser(spec).delete(user.getUsername());
    }
}
