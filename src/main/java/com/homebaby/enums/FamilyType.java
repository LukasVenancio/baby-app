package com.homebaby.enums;

import lombok.Getter;

@Getter
public enum FamilyType {
    ADOPTIVE_FAMILY("ADOPTIVE_FAMILY"),
    TRADITIONAL_FAMILY("TRADITIONAL_FAMILY"),
    HOMOAFFECTIVE_FAMILY("HOMOAFFECTIVE_FAMILY"),
    SINGLE_PARENT_FAMILY("SINGLE_PARENT_FAMILY"),
    FAMILY_WITH_NON_BINARY_MEMBER("FAMILY_WITH_NON_BINARY_MEMBER"),
    FAMILY_WITH_TRANSGENDER_PARENT("FAMILY_WITH_TRANSGENDER_PARENT");

    private final String familyType;

    FamilyType(String familyType){
        this.familyType = familyType;
    }
}
