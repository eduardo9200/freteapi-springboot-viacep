package com.wipro.freteapi.application.enums;

import com.wipro.freteapi.application.validators.StringValidator;

public enum ResponseFormat {
	JSON("json"), XML("xml");
	
	private String format;
	
	private ResponseFormat(String format) {
		this.format = format;
	}
	
	public String getFormat() {
		return this.format;
	}
	
	public static ResponseFormat valueByFormat(String format) {
		if(!StringValidator.isValid(format)) {
			throw new RuntimeException("Format is invalid.");
		}
		
		format = format.toLowerCase();
		
		for(ResponseFormat rf : values()) {
			if(rf.getFormat().equals(format)) {
				return rf;
			}
		}
		
		throw new RuntimeException("Format not found.");
	}
}
