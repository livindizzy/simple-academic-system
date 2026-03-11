package com.academic.simple_academic_system.exception;

import com.academic.simple_academic_system.model.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Response<String>> handleBadRequestException(BadRequestException e) {
        Response<String> response = Response.<String>builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .body(null)
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleResponseStatusException(ResponseStatusException e) {
        Response<String> response = Response.<String>builder()
                .httpStatus(HttpStatus.valueOf(e.getStatusCode().value()))
                .statusCode(e.getStatusCode().value())
                .message("Tidak bisa akses")
                .body(e.getReason())
                .build();
        return new ResponseEntity<>(response, HttpStatus.valueOf(e.getStatusCode().value()));
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<Response<String>> handleMissingRequestHeaderException(MissingRequestHeaderException e) {
        Response<String> response = Response.<String>builder()
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .message("Tidak bisa akses")
                .body(e.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        Response<String> response = Response.<String>builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Internal Server Error")
                .body(e.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
