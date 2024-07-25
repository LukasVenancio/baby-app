package com.homebaby.enums;

import lombok.Getter;

@Getter
public enum GestationType {
    SPONTANEOUS("SPONTANEOUS"),
    FERTILIZED("FERTILIZED");

    private final String name;

    GestationType(String name) {
        this.name = name;
    }
}
