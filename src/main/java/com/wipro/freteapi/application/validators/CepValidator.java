package com.wipro.freteapi.application.validators;

import com.wipro.freteapi.application.utils.CepUtil;

public class CepValidator {
	
	/**
	 * A CEP format is considered valid if its length equals 8
	 * and it have digits only.
	 * 
	 * @return {@code true} if CEP is valid,
	 * 		   otherwise {@code false}
	 * */
	public static boolean isFormatValid(String cep) {
		if(!StringValidator.isValid(cep)) {
			return false;
		}
		
		String newCep = CepUtil.removeMaskAndChars(cep);
		
		if(newCep.length() != 8) {
			return false;
		}
		
		return true;
	}
}
