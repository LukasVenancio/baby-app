package com.homebaby.errors.exceptions;

import com.homebaby.errors.ExceptionCode;
import lombok.Getter;

@Getter
public class BusinessRuleException extends RuntimeException {
    private final ExceptionCode code;
    private final String[] customMessageFields;

    public BusinessRuleException(String message, ExceptionCode code, String... customMessageFields) {
        super(message);
        this.code = code;
        this.customMessageFields = customMessageFields;
    }

    public BusinessRuleException(ExceptionCode code, String... customMessageFields) {
        super("Business exception");
        this.code = code;
        this.customMessageFields = customMessageFields;
    }


    public String getCodeAsString() {
        return code.toString();
    }

}

