package com.homebaby.enums;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("MALE"),
    FEMALE("FEMALE"),
    UNKNOWN("UNKNOWN");

    private final String name;

    Gender(String name) {
        this.name = name;
    }
}
