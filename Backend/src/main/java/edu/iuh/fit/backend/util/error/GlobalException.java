/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package edu.iuh.fit.backend.util.error;

import edu.iuh.fit.backend.domain.ApiResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/*
 * @description
 * @author: Huu Thai
 * @date:
 * @version: 1.0
 */
@RestControllerAdvice
public class GlobalException {

    /*
     * handle the rest exceptions
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> hanldeAllException(Exception ex) {
        var result = new ApiResponse<>(
                HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
                null, "handleAllException"
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errorList = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        String errors = String.join("; ", errorList);

        ApiResponse<Object> response =  new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),
                errorList.size() > 1 ? errorList : errorList.getFirst(),
                null,
                ex.getBody().getDetail());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiResponse<?>> handleNotFound(NoSuchElementException ex) {
        var result = new ApiResponse<>(
                HttpStatus.NOT_FOUND.value(),
                "handleNotFound",
                null, ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }


}
