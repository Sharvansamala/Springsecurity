package org.spring.springjpa.securityspring.advice;

import io.jsonwebtoken.JwtException;
import org.spring.springjpa.securityspring.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ApiError apiError = new ApiError(ex.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handleAuthenticationException(AuthenticationException ex) {
        ApiError apiError = new ApiError("Authentication failed: " + ex.getMessage(), HttpStatus.UNAUTHORIZED);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
    }

   @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiError> handleJwtException(JwtException ex) {
        ApiError apiError = new ApiError(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
    }
}
