package com.dcardozo.teburuportal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class HandlerException {

    private MessageException builResponse(String message, HttpStatus httpStatus) {
        return new MessageException(message, httpStatus.value());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleDataNotFound(HttpServletRequest request,
                                                NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(builResponse(e.getMessage(), HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(ErrorProcessException.class)
    public ResponseEntity<?> handleDataConflict(HttpServletRequest request,
                                                ErrorProcessException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(builResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequest(HttpServletRequest request,
                                              BadRequestException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(builResponse(e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> argumentNotValidRequest(HttpServletRequest request,
                                                     MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(builResponse(e.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST));
    }

}
