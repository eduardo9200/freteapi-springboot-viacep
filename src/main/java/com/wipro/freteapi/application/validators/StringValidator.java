package com.wipro.freteapi.application.validators;

public class StringValidator {
	
	/**
	 * The String is considered valid if it have one or more characters;
	 * 
	 * @return {@code true} if the string is not {@code null} and not blank,
	 * 		   otherwise {@code false}
	 * */
	public static boolean isValid(String str) {
		if(str == null || str.isBlank()) {
			return false;
		}
		
		return true;
	}
}
