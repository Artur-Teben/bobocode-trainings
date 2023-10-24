package com.bobocode.simplerestdemo.exception.handler;

import com.bobocode.simplerestdemo.exception.UserNotFoundException;
import com.bobocode.simplerestdemo.exception.model.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFoundException(UserNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity
                .badRequest()
                .body(
                        ApiError.builder()
                        .code(1)
                        .message(exception.getMessage())
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .build()
                );
    }
}
