package com.example.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class IncorrectCurrencyException extends RuntimeException {

    public IncorrectCurrencyException(String message) {
        super(message);
    }
}
