package com.wipro.freteapi.junit;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wipro.freteapi.application.enums.FretePrice;
import com.wipro.freteapi.application.exceptions.CepException;
import com.wipro.freteapi.application.exceptions.NotFoundException;
import com.wipro.freteapi.domain.frete.model.Frete;
import com.wipro.freteapi.domain.frete.service.FreteService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FreteapiApplicationJUnitTests {

	@Autowired
	private FreteService freteService;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void findValidCepOnViacepApi() {
		Frete freteNorte       = new Frete("69900-088", "Avenida Ceará", "de 957 a 1857 - lado ímpar", "Centro", "Rio Branco", "AC", BigDecimal.valueOf(FretePrice.NORTE.getPrice()));
		Frete freteNordeste    = new Frete("60060-170", "Rua São José", "", "Centro", "Fortaleza", "CE", BigDecimal.valueOf(FretePrice.NORDESTE.getPrice()));
		Frete freteSul         = new Frete("90010-030", "Rua General João Manoel", "", "Centro Histórico", "Porto Alegre", "RS", BigDecimal.valueOf(FretePrice.SUL.getPrice()));
		Frete freteSudeste     = new Frete("01001-000", "Praça da Sé", "lado ímpar", "Sé", "São Paulo", "SP", BigDecimal.valueOf(FretePrice.SUDESTE.getPrice()));
		Frete freteCentroOeste = new Frete("70403-900", "SES 801 Lote 03", "", "Asa Sul", "Brasília", "DF", BigDecimal.valueOf(FretePrice.CENTRO_OESTE.getPrice()));
		
		Frete freteFromApi = null;
		try {
			freteFromApi = this.freteService.consultFretePriceByCep("69900-088", null);
			Assertions.assertEquals(freteNorte, freteFromApi);
			
			freteFromApi = this.freteService.consultFretePriceByCep("60060170", null);
			Assertions.assertEquals(freteNordeste, freteFromApi);
			
			freteFromApi = this.freteService.consultFretePriceByCep("90.010-030", null);
			Assertions.assertEquals(freteSul, freteFromApi);
			
			freteFromApi = this.freteService.consultFretePriceByCep("01001000", null);
			Assertions.assertEquals(freteSudeste, freteFromApi);
			
			freteFromApi = this.freteService.consultFretePriceByCep("CEP: 70403900", null);
			Assertions.assertEquals(freteCentroOeste, freteFromApi);
		} catch (JsonProcessingException | CepException e) {
			Assertions.assertEquals(null, freteFromApi);
		}
	}
	
	@Test
	public void mustThrowCepExceptionForInvalidCep() {
		Assertions.assertThrows(CepException.class, () ->
		  this.freteService.consultFretePriceByCep("1234567890", null));
		
		Assertions.assertThrows(CepException.class, () ->
		  this.freteService.consultFretePriceByCep("12345", null));
		
		Assertions.assertThrows(CepException.class, () ->
		  this.freteService.consultFretePriceByCep("12345678", null));
	}
	
	@Test
	public void mustThrowNotFoundExceptionForInexistentCep() {
		Assertions.assertThrows(NotFoundException.class, () ->
		  this.freteService.consultFretePriceByCep("99999999", null));
		
		Assertions.assertThrows(NotFoundException.class, () ->
		  this.freteService.consultFretePriceByCep("99999-999", null));
		
		Assertions.assertThrows(NotFoundException.class, () ->
		  this.freteService.consultFretePriceByCep("99.999-999", null));
	}
}
