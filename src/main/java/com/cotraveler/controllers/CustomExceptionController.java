/*
 * Copyright (c)  Aleksey Plekhanov 03.02.2023, 03:14
 */

package com.cotraveler.controllers;

import com.cotraveler.exceptions.CustomException;
import com.cotraveler.exceptions.CustomExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomExceptionResponse> customExceptionHandle(Exception ex, WebRequest request) {

        CustomExceptionResponse exception = new CustomExceptionResponse();
        exception.setTimestamp(new Date());
        exception.setMessage(ex.getMessage());
        exception.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

}
