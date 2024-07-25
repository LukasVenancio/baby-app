package com.homebaby.errors.exceptions;

import com.homebaby.errors.ExceptionCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class InvalidFieldException extends RuntimeException {
    private final ExceptionCode code;
    private final String[] customMessageFields;

    public InvalidFieldException(String fieldName) {
        super(ExceptionCode.INVALID_FIELD.toString());
        this.code = ExceptionCode.INVALID_FIELD;
        this.customMessageFields = new String[]{fieldName};
        log.error("Invalid Field:", this);
    }

    public InvalidFieldException(ExceptionCode code, String... customMessageFields) {
        super("Invalid fields");
        this.code = code;
        this.customMessageFields = customMessageFields;
    }

    public String getCodeAsString() {
        return code.toString();
    }

    public Object[] getArgs() {
        return customMessageFields;
    }
}
