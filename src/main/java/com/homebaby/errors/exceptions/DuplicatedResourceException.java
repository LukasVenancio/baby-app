package com.homebaby.errors.exceptions;

import com.homebaby.errors.ExceptionCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class DuplicatedResourceException extends RuntimeException {
    private final String[] customMessageFields;
    private final ExceptionCode code;

    public DuplicatedResourceException(Class entityClass) {
        super(ExceptionCode.DUPLICATED_RESOURCE.toString());
        this.code = ExceptionCode.DUPLICATED_RESOURCE;
        this.customMessageFields = new String[] { entityClass.getSimpleName() };

        log.error(this.getMessage(), this);
    }

    public String getCodeAsString() {
        return code.toString();
    }

    public Object[] getArgs() {
        return customMessageFields;
    }
}
