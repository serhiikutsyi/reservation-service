package com.serhiikutsyi.reservation.controller;

import com.serhiikutsyi.reservation.exception.ReservationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.joining;

/**
 * @author Serhii Kutsyi
 */
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<Object> handleException(HttpServletRequest request, ReservationNotFoundException exception) {
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", exception.getMessage());
        responseBody.put("request", request.getMethod() + " " + request.getRequestURI());
        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleException(HttpServletRequest request, MethodArgumentNotValidException exception) {
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", "Validation failed. " + exception.getBindingResult().getErrorCount() + " error(s). " + exception.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(joining(",")));
        responseBody.put("request", request.getMethod() + " " + request.getRequestURI());
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(HttpServletRequest request, Exception exception) {
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", exception.getMessage());
        responseBody.put("request", request.getMethod() + " " + request.getRequestURI());
        return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
