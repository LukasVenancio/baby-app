package com.homebaby.errors.exceptions;

import com.homebaby.errors.ExceptionCode;
import jakarta.mail.MessagingException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Getter
@Slf4j
public class EmailNotSentException extends RuntimeException {
    private final MessagingException messagingException;
    private final ExceptionCode code;
    private final String[] customMessageFields;

    public EmailNotSentException(List<String> to, MessagingException ex) {
        super(ExceptionCode.EMAIL_NOT_SENT.toString());
        this.messagingException = ex;
        this.code = ExceptionCode.EMAIL_NOT_SENT;
        this.customMessageFields = new String[] { String.join(", ", to) };

        log.error(this.getMessage(), this);
    }

    public String getCodeAsString() {
        return code.toString();
    }

    public Object[] getArgs() {
        return customMessageFields;
    }
}
