package com.gab26.errorRepository.exception.handler;

import com.gab26.errorRepository.exception.ExceptionResponse;
import com.gab26.errorRepository.exception.NoInformeById;
import com.gab26.errorRepository.exception.NoMoreInformeException;
import com.gab26.errorRepository.exception.handleNoRelvants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleAllExceptions(Exception e, WebRequest request){
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(
                        new Date(),
                        e.getMessage(),
                        request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoMoreInformeException.class)
    public ResponseEntity<ExceptionResponse> handleNoMoreInformeExceptions(Exception e, WebRequest request){
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(
                        new Date(),
                        e.getMessage(),
                        request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoInformeById.class)
    public ResponseEntity<ExceptionResponse> handleNoInformeById(Exception e, WebRequest request){
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(
                        new Date(),
                        e.getMessage(),
                        request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(handleNoRelvants.class)
    public ResponseEntity<ExceptionResponse> handleNoRelvants(Exception e, WebRequest request){
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(
                        new Date(),
                        e.getMessage(),
                        request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
