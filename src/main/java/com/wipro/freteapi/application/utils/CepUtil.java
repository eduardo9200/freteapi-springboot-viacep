package com.wipro.freteapi.application.utils;

import com.wipro.freteapi.application.exceptions.CepException;
import com.wipro.freteapi.application.validators.CepValidator;
import com.wipro.freteapi.application.validators.StringValidator;

public class CepUtil {
	
	/**
	 * Remove all characters of the string, except digits.
	 * 
	 * <p>
	 * Examples:
	 * <blockquote><pre>
     * "12345-000" returns "12345000"
     * "12.345-000" returns "12345000"
     * "abc123 d45ef" returns "12345"
     * </pre></blockquote>
     * 
	 * @return a empty string if CEP is {@code null},
	 *         otherwise the string with only digits.
	 * */
	public static String removeMaskAndChars(String cep) {
		if(cep == null) {
			return "";
		}
		
		String str = cep.replaceAll("[^0-9]", "");
		return str;
	}
	
	/**
	 * Return the CEP on format "00000-000" if it is valid
	 * 
	 * @return {@code null} if CEP is {@code null} or blank,
	 *         otherwise the CEP on format "00000-000"
	 * @throws CepException if CEP format is not valid
	 * */
	public static String inputCepMask(String cep) throws CepException {
		if(!StringValidator.isValid(cep)) {
			return null;
		}
		
		if(CepValidator.isFormatValid(cep))
			return cep.substring(0, 5) + "-" + cep.substring(5);
		
		throw new CepException("CEP format is not valid");
	}
}
