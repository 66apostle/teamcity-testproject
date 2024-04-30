package com.example.teamcity_testproject.api.generators;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomData {

    public static final int LENGTH = 10;

    public static String getString() {
        return "test_" + RandomStringUtils.randomAlphabetic(LENGTH);
    }
}
