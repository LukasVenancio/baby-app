package com.homebaby.errors;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.homebaby.errors.exceptions.*;
import com.homebaby.errors.i18n.Message;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    private final Message messageService;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        var messages = ex.getBindingResult().getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toArray(String[]::new);
        ;

        var field = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getField();

        ErrorResponse body =
                new ErrorResponse()
                        .withError(ExceptionCode.API_FIELDS_INVALID)
                        .withMessage(messageService.get(ExceptionCode.API_FIELDS_INVALID, field))
                        .withDetails(messages);

        return handleExceptionInternal(ex, body, headers, status, request);
    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    public ResponseEntity<Object> badCredentialsExceptionHandler(
            Exception ex, WebRequest request
    ) {
        ErrorResponse body = new ErrorResponse()
                .withError(ExceptionCode.BAD_CREDENTIALS)
                .withMessage(messageService.get(ExceptionCode.BAD_CREDENTIALS, new String[]{}));

        log.error(ex.getMessage(), ex);
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> internalServerErrorExceptionHandler(
            Exception ex, WebRequest request) {
        ErrorResponse body =
                new ErrorResponse()
                        .withError(ExceptionCode.INTERNAL_SERVER_ERROR)
                        .withMessage(messageService.get(ExceptionCode.INTERNAL_SERVER_ERROR));

        log.error(ex.getMessage(), ex);

        return handleExceptionInternal(
                ex, body, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = {BusinessRuleException.class})
    public ResponseEntity<Object> businessExceptionHandler(BusinessRuleException ex, WebRequest request) {
        ErrorResponse body = new ErrorResponse()
                .withError(ex.getCode())
                .withMessage(messageService.get(ex.getCodeAsString(), ex.getCustomMessageFields()))
                .withDetails(ex.getCustomMessageFields());

        log.error(ex.getMessage(), ex);
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    @ExceptionHandler(value = {InvalidFieldException.class})
    public ResponseEntity<Object> invalidFieldExceptionHandler(InvalidFieldException ex, WebRequest request) {
        ErrorResponse body = new ErrorResponse()
                .withError(ex.getCode())
                .withMessage(messageService.get(ex.getCodeAsString(), ex.getCustomMessageFields()));
        log.error(ex.getMessage(), ex);
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ResponseEntity<Object> dataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request) {
        var cause = Objects.nonNull(ex.getCause().getCause()) ? ex.getCause().getCause() : ex.getCause();
        ErrorResponse body = new ErrorResponse()
                .withError(ExceptionCode.API_FIELDS_INVALID)
                .withMessage(messageService.get(ExceptionCode.API_FIELDS_INVALID.toString()))
                .withDetails(new String[]{Objects.nonNull(cause.getMessage()) ? cause.getMessage() : ex.getMessage()});

        log.error(ex.getMessage(), ex);
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {DuplicateKeyException.class})
    public ResponseEntity<Object> duplicateKeyException(DuplicateKeyException ex, WebRequest request) {
        var cause = Objects.nonNull(ex.getCause().getCause()) ? ex.getCause().getCause() : ex.getCause();
        ErrorResponse body = new ErrorResponse()
                .withError(ExceptionCode.DUPLICATED_RESOURCE)
                .withMessage(messageService.get(ExceptionCode.DUPLICATED_RESOURCE.toString()))
                .withDetails(new String[]{Objects.nonNull(cause.getMessage()) ? cause.getMessage() : ex.getMessage()});

        log.error(ex.getMessage(), ex);
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }


    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Object> entityNotFound(EntityNotFoundException ex, WebRequest request) {
        ErrorResponse body = new ErrorResponse()
                .withError(ExceptionCode.ENTITY_NOT_FOUND)
                .withMessage(messageService.get(ExceptionCode.ENTITY_NOT_FOUND.toString(), ex.getCustomMessageFields()));

        log.error(ex.getMessage(), ex);
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {ForbiddenException.class})
    public ResponseEntity<Object> forbidden(ForbiddenException ex, WebRequest request) {
        ErrorResponse body = new ErrorResponse()
                .withError(ExceptionCode.FORBIDDEN)
                .withMessage(messageService.get(ExceptionCode.FORBIDDEN.toString(), ex.getCustomMessageFields()));

        log.error(ex.getMessage(), ex);
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }


    @ExceptionHandler(value = {InvalidRequestException.class})
    public ResponseEntity<Object> invalidRequestException(InvalidRequestException ex, WebRequest request) {
        ErrorResponse body = new ErrorResponse()
                .withError(ExceptionCode.INVALID_REQUEST_EXCEPTION)
                .withMessage(messageService.get(ExceptionCode.INVALID_REQUEST_EXCEPTION.toString()));

        log.error(ex.getMessage(), ex);
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


    @ExceptionHandler(value = {DuplicatedResourceException.class})
    public ResponseEntity<Object> entityNotFound(DuplicatedResourceException ex, WebRequest request) {
        ErrorResponse body = new ErrorResponse()
                .withError(ExceptionCode.DUPLICATED_RESOURCE)
                .withMessage(messageService.get(ExceptionCode.DUPLICATED_RESOURCE.toString(), ex.getCustomMessageFields()));

        log.error(ex.getMessage(), ex);
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = {EmailNotSentException.class})
    public ResponseEntity<Object> emailNotSent(EmailNotSentException ex, WebRequest request) {
        ErrorResponse body = new ErrorResponse()
                .withError(ExceptionCode.EMAIL_NOT_SENT)
                .withMessage(messageService.get(ExceptionCode.EMAIL_NOT_SENT.toString(), ex.getCustomMessageFields()));

        log.error(ex.getMessage(), ex);
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }


    @ExceptionHandler(value = {UnauthorizedException.class})
    public ResponseEntity<Object> unauthorized(UnauthorizedException ex, WebRequest request) {
        ErrorResponse body = new ErrorResponse()
                .withError(ExceptionCode.UNAUTHORIZED)
                .withMessage(messageService.get(ExceptionCode.UNAUTHORIZED.toString()));

        log.error(ex.getMessage(), ex);
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(value = {ExpiredTokenException.class})
    public ResponseEntity<Object> expiredTokenException(ExpiredTokenException ex, WebRequest request) {
        ErrorResponse body = new ErrorResponse()
                .withError(ExceptionCode.EXPIRED_TOKEN)
                .withMessage(messageService.get(ExceptionCode.EXPIRED_TOKEN.toString()));

        log.error(ex.getMessage(), ex);
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {UsedTokenException.class})
    public ResponseEntity<Object> expiredTokenException(UsedTokenException ex, WebRequest request) {
        ErrorResponse body = new ErrorResponse()
                .withError(ExceptionCode.USED_TOKEN)
                .withMessage(messageService.get(ExceptionCode.USED_TOKEN.toString()));

        log.error(ex.getMessage(), ex);
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {AuthenticationTokenCreationException.class})
    public ResponseEntity<Object> smsNotSent(AuthenticationTokenCreationException ex, WebRequest request) {
        ErrorResponse body = new ErrorResponse()
                .withError(ExceptionCode.AUTHENTICATION_TOKEN_CREATION_ERROR)
                .withMessage(messageService.get(ExceptionCode.AUTHENTICATION_TOKEN_CREATION_ERROR.toString()));

        log.error(ex.getMessage(), ex);
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        var cause = ex.getCause().getCause();
        ErrorResponse body = new ErrorResponse()
                .withMessage(messageService.get(ExceptionCode.API_FIELDS_INVALID.toString(),
                        new String[]{Objects.nonNull(cause.getMessage()) ? cause.getMessage() : ex.getMessage()}))
                .withError(ExceptionCode.API_FIELDS_INVALID);

        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Object> illegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        ErrorResponse body;

        if (ex.getCause().getClass().equals(UnrecognizedPropertyException.class)) {
            body = new ErrorResponse()
                    .withError(ExceptionCode.JSON_MAPPING_ERROR)
                    .withMessage(messageService.get(ExceptionCode.JSON_MAPPING_ERROR.toString()));

            log.error(ex.getMessage(), ex);
            return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
        }

        body = new ErrorResponse()
                .withError(ExceptionCode.API_FIELDS_INVALID)
                .withMessage(messageService.get(ExceptionCode.API_FIELDS_INVALID.toString()));
        log.error(ex.getMessage(), ex);
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}

