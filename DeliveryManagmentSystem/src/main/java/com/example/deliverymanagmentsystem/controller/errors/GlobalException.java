package com.example.deliverymanagmentsystem.controller.errors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalException {

    @Autowired
    private MessageSource messageSource;
    @ExceptionHandler(value = {ResourceNotFoundException.class, EmptyResultDataAccessException.class, IncorrectResultSizeDataAccessException.class})
    public ResponseEntity<?> handleResourceNotFoundException (ResourceNotFoundException ex) {
        String message = messageSource.getMessage(ex.getErrorCode(), null, LocaleContextHolder.getLocale());
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", message);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(messageSource.getMessage(error.getCode(),null, LocaleContextHolder.getLocale()));
        }
        ValidationErrorResponse response = new ValidationErrorResponse(HttpStatus.BAD_REQUEST, errors.get(0).toString(), errors);
        return ResponseEntity.badRequest().body(response);
    }




}
