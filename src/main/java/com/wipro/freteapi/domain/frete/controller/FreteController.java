package com.wipro.freteapi.domain.frete.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.wipro.freteapi.application.enums.ResponseFormat;
import com.wipro.freteapi.application.exceptions.CepException;
import com.wipro.freteapi.application.exceptions.NotFoundException;
import com.wipro.freteapi.domain.frete.model.Frete;
import com.wipro.freteapi.domain.frete.service.FreteService;

@RestController
@RequestMapping("frete")
public class FreteController {

	@Autowired
	private FreteService freteService;
	
	@GetMapping(value = {"", "/", "/json"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Frete> consultFretePriceByCepWithJsonResponse(@RequestParam("cep") String cep)
			throws CepException, NotFoundException, JsonMappingException, JsonProcessingException {
		return ResponseEntity
			    .ok(
				  freteService.consultFretePriceByCep(cep, ResponseFormat.JSON.getFormat())
			    );
	}
	
	@GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Frete> consultFretePriceByCepWithXmlResponse(@RequestParam("cep") String cep)
			throws CepException, NotFoundException, JsonMappingException, JsonProcessingException {
		return ResponseEntity
			    .ok(
				  freteService.consultFretePriceByCep(cep, ResponseFormat.XML.getFormat())
			   );
	}
}
