package com.zrip.google_map_service.util.exceptions.handler;

import com.zrip.google_map_service.util.exceptions.GoogleMapServiceException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GoogleMapServiceExceptionHandler {
    @ExceptionHandler({GoogleMapServiceException.class})
    public ResponseEntity<Object> handle(GoogleMapServiceException e) {
        return new ResponseEntity<>(ExceptionResponse.from(e, e.getStatus()), e.getStatus());
    }

    @Data
    @Builder
    public static class ExceptionResponse {
        private String message;
        private Integer status;

        public static ExceptionResponse from(Exception e, HttpStatus status) {
            return ExceptionResponse.builder()
                    .message(e.getMessage())
                    .status(status.value())
                    .build();
        }
    }
}
