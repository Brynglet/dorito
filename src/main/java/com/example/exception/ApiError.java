package com.example.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatusCode;

import java.time.ZonedDateTime;

public class ApiError extends RuntimeException {

    private final HttpStatusCode httpStatusCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private final ZonedDateTime timestamp;
    private final String message;

    public ApiError(HttpStatusCode httpStatusCode, String message) {
        super();
        this.httpStatusCode = httpStatusCode;
        this.timestamp = ZonedDateTime.now();
        this.message = message;
    }
}