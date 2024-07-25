package com.homebaby.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MaritalStatus {
    SINGLE("SINGLE"),
    MARRIED("MARRIED"),
    DIVORCED("DIVORCED"),
    WIDOWED("WIDOWED"),
    COMMON_LAW("COMMON_LAW");

    private final String name;
}
