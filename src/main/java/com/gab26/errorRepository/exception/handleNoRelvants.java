package com.gab26.errorRepository.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class handleNoRelvants extends RuntimeException{

    public handleNoRelvants(String exception){
        super(exception);
    }
}
