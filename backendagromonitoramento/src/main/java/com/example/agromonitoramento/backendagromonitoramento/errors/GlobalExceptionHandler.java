package com.example.agromonitoramento.backendagromonitoramento.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> tratarErros(IllegalArgumentException e){

       ErrorResponse errorResponse = new ErrorResponse(
               HttpStatus.BAD_REQUEST.value(), //400
               "VALIDACAO",
               e.getMessage()
       );

       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }




    //SenhaInvalidaException, NullPointerException, RuntimeException

}
