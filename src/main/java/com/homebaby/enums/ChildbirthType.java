package com.homebaby.enums;

import lombok.Getter;

@Getter
public enum ChildbirthType {
    NORMAL("NORMAL"),
    CAESAREAN("CAESAREAN"),
    FORCEPS("FORCEPS");

    private final String type;

    ChildbirthType(String type){ this.type = type; }
}
