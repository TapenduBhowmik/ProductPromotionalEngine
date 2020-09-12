package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(NoProductInCartException.class)
	public ResponseEntity<ModelExceptionResponse> handleNoProductInCartException(NoProductInCartException ex){
		ModelExceptionResponse model = new ModelExceptionResponse();
		model.setErrCode("Error code 1");
		model.setErrMessage(ex.getMessage());
        return new ResponseEntity<ModelExceptionResponse>(model,HttpStatus.BAD_REQUEST);
	}

}
