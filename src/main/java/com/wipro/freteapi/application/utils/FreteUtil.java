package com.wipro.freteapi.application.utils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.wipro.freteapi.application.enums.FretePrice;

public class FreteUtil {

	/*
	 * States separated by region
	 * */
	private static List<String> norte       = Arrays.asList("AC", "AM", "AP", "PA", "RO", "RR", "TO");
	private static List<String> nordeste    = Arrays.asList("AL", "BA", "CE", "MA", "PB", "PE", "PI", "RN", "SE");
	private static List<String> sul         = Arrays.asList("PR", "RS", "SC");
	private static List<String> sudeste     = Arrays.asList("ES", "MG", "RJ", "SP");
	private static List<String> centroOeste = Arrays.asList("DF", "GO", "MS", "MT");
	
	/**
	 * Calculate frete price by region, based on state attribute from ViaCep class.
	 * 
	 * @return the frete price of state region.
	 * */
	public static BigDecimal fretePrice(String state) {
		BigDecimal frete = null;
		
		if(norte.contains(state)) {
			frete = BigDecimal.valueOf(FretePrice.NORTE.getPrice());
		} else if(nordeste.contains(state)) {
			frete = BigDecimal.valueOf(FretePrice.NORDESTE.getPrice());
		} else if(sul.contains(state)) {
			frete = BigDecimal.valueOf(FretePrice.SUL.getPrice());
		} else if(sudeste.contains(state)) {
			frete = BigDecimal.valueOf(FretePrice.SUDESTE.getPrice());
		} else if(centroOeste.contains(state)) {
			frete = BigDecimal.valueOf(FretePrice.CENTRO_OESTE.getPrice());
		}
		
		return frete;
	}
}
