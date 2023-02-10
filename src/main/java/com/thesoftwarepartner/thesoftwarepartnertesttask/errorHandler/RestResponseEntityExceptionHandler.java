package com.thesoftwarepartner.thesoftwarepartnertesttask.errorHandler;

import com.thesoftwarepartner.thesoftwarepartnertesttask.dto.ErrorDto;
import com.thesoftwarepartner.thesoftwarepartnertesttask.errorHandler.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDto> handleNotSpecifiedRuntimeException(RuntimeException e) {
        return new ResponseEntity<>(new ErrorDto(
                "UNKNOWN_ERROR",
                e.getMessage()
        ), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> handleResourceNotFound(ResourceNotFoundException e) {
        return new ResponseEntity<>(new ErrorDto(
                "RESOURCE_NOT_FOUND",
                e.getMessage()
        ), HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        return handleExceptionInternal(ex, getErrorsFromException(ex), headers, status, request);

    }

    private Map<String, ErrorDto> getErrorsFromException(MethodArgumentNotValidException ex) {
        Map<String, ErrorDto> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            errors.put(fieldName, new ErrorDto("VALIDATION_ERROR", error.getDefaultMessage()));
        });

        return errors;
    }
}
