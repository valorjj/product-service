package com.example.productservice.exception;

import com.example.productservice.model.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

public class ProductServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductServiceCustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(ProductServiceCustomException e) {
        return ResponseEntity
            .status(NOT_FOUND)
            .body(ErrorResponse.builder()
                .errorMessage(e.getMessage())
                .errorCode(e.getErrorCode()).build()
            );
    }

}
