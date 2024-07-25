package com.homebaby.errors.exceptions;

import com.homebaby.errors.ExceptionCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class AmountOfMonthsMustBeTheSameException extends RuntimeException{
    private final ExceptionCode code;
    private final String[] customMessageFields;

    public AmountOfMonthsMustBeTheSameException(String title) {
        super(ExceptionCode.AMOUNT_OF_MONTHS_MUST_BE_THE_SAME_OF_A3.toString());
        this.code = ExceptionCode.AMOUNT_OF_MONTHS_MUST_BE_THE_SAME_OF_A3;
        this.customMessageFields = new String[] { title };

        log.error(this.getMessage(), this);
    }

    public String getCodeAsString() {
        return code.toString();
    }

    public Object[] getArgs() {
        return customMessageFields;
    }
}
