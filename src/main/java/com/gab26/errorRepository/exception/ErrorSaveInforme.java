package com.gab26.errorRepository.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ErrorSaveInforme extends RuntimeException{


    public ErrorSaveInforme(String exception){
        super(exception);
    }
}
