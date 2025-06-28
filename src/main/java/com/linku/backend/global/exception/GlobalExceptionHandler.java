package com.linku.backend.global.exception;

import com.linku.backend.global.response.BaseResponse;
import com.linku.backend.global.response.ResponseCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?> handle404() {
        return ResponseEntity.status(404).body(
                BaseResponse.of(ResponseCode.NOT_FOUND, null)
        );
    }

    @ExceptionHandler(LinkuException.class)
    public ResponseEntity<?> handleLinkuException(LinkuException ex) {
        return ResponseEntity.status(400).body(
                BaseResponse.of(false, ex.getCode(), ex.getMessage(), null)
        );
    }
}