package com.wipro.freteapi.application.exceptions;

public class NotFoundException extends CepException {
	private static final long serialVersionUID = 1L;

	public NotFoundException() {
		super();
	}
	
	public NotFoundException(String msg) {
		super(msg);
	}
}
