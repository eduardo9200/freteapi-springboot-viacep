package com.wipro.freteapi.application.enums;

import com.wipro.freteapi.application.validators.StringValidator;

public enum FretePrice {

	NORTE("NORTE", 20.83),
	NORDESTE("NORDESTE", 15.98),
	SUL("SUL", 17.30),
	SUDESTE("SUDESTE", 7.85),
	CENTRO_OESTE("CENTRO_OESTE", 12.50);
	
	private String region;
	private double price;
	
	private FretePrice(String region, double price) {
		this.region = region;
		this.price = price;
	}

	public String getRegion() {
		return region;
	}
	
	public double getPrice() {
		return price;
	}

	public static FretePrice valueByRegion(String region) {
		if(!StringValidator.isValid(region)) {
			throw new RuntimeException("Region not valid.");
		}
		
		region = region.toUpperCase();
		
		for(FretePrice tf : values()) {
			if(tf.getRegion().equals(region)) {
				return tf;
			}
		}
		
		throw new RuntimeException("Region and frete price not found.");
	}
}
