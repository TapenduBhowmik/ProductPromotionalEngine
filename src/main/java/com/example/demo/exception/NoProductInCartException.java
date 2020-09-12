package com.example.demo.exception;

public class NoProductInCartException extends Exception {
	
	public NoProductInCartException() {
		
	}
	
	public NoProductInCartException(String message) {
		super(message);
	}

}
