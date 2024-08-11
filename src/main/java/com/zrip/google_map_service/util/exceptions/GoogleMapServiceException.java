package com.zrip.google_map_service.util.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GoogleMapServiceException extends RuntimeException {
    HttpStatus status;

    public GoogleMapServiceException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
