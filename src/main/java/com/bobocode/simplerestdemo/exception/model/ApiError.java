package com.bobocode.simplerestdemo.exception.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ApiError {

    private int code;

    private String message;

    private HttpStatus httpStatus;
}
