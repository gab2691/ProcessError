package com.gab26.errorRepository.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoMoreInformeException extends RuntimeException{

    public NoMoreInformeException(String message){
        super(message);
    }
}
