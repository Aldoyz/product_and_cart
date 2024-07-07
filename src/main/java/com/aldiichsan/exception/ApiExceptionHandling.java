package com.aldiichsan.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ApiExceptionHandling {

    private static final Logger log = LogManager.getLogger(ApiExceptionHandling.class);

    public void ExceptionHandling (Exception e) {
        if (e instanceof DuplicateKeyException) {
            log.error("Duplicate Key Exception : " + e.getMessage());
            throw new ApiException.Conflict(e.getMessage());
        } else if (e instanceof NullPointerException) {
            log.error("Null Pointer Exception : " + e.getMessage());
            throw new ApiException.NullPointer(e.getMessage());
        } else if (e instanceof ArrayIndexOutOfBoundsException) {
            log.error("Array Index Out of Bounds Exception : " + e.getMessage());
            throw new ApiException.ArrayIndexOutOfBounds(e.getMessage());
        } else if (e instanceof IndexOutOfBoundsException) {
            log.error("Index Out of Bounds Exception : " + e.getMessage());
            throw new ApiException.IndexOutOfBounds(e.getMessage());
        } else if (e instanceof ArithmeticException) {
            log.error("Arithmetic Exception : " + e.getMessage());
            throw new ApiException.Arithmetic(e.getMessage());
        } else {
            log.error("Unexpected Error Happened : " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
