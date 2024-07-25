package com.homebaby.errors.exceptions;

import com.homebaby.errors.ExceptionCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class EntityNotFoundException extends RuntimeException {
    private final String[] customMessageFields;
    private final ExceptionCode code;

    public EntityNotFoundException(Class entityClass) {
        super(ExceptionCode.ENTITY_NOT_FOUND.toString());
        this.code = ExceptionCode.ENTITY_NOT_FOUND;
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
