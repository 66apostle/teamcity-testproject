package com.example.teamcity_testproject.api.models;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {

    private String username;

    private String password;

    private String email;

    private Roles roles;

}
