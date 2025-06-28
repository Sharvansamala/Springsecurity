package org.spring.springjpa.securityspring.advice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {
    private LocalDateTime timestamp;
    private String error;
    private HttpStatus status;

    public ApiError(){
        this.timestamp = LocalDateTime.now();
    }
    public ApiError(String error, HttpStatus status) {
        this();
        this.error = error;
        this.status = status;
    }
}
