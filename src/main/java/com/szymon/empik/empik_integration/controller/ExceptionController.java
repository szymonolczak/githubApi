package com.szymon.empik.empik_integration.controller;

import com.szymon.empik.empik_integration.model.ErrorMessage;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import javax.persistence.PersistenceException;

@ControllerAdvice
public class ExceptionController implements ErrorController {

    @ExceptionHandler(value = {HttpClientErrorException.class})
    public ResponseEntity<String> httpClientExceptionHandler(HttpClientErrorException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {PersistenceException.class})
    public ResponseEntity<ErrorMessage> hibernateExceptionHandler(PersistenceException ex) {
        ErrorMessage errorMessage = new ErrorMessage(ExceptionUtils.getStackTrace(ex), ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
