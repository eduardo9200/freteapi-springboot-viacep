package com.wipro.freteapi.application.exceptions;

public class CepException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CepException() {
		super();
	}
	
	public CepException(String msg) {
		super(msg);
	}
}
