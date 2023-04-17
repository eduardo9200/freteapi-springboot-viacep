package com.wipro.freteapi.domain.frete.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.wipro.freteapi.application.enums.ResponseFormat;
import com.wipro.freteapi.application.exceptions.CepException;
import com.wipro.freteapi.application.exceptions.NotFoundException;
import com.wipro.freteapi.application.utils.CepUtil;
import com.wipro.freteapi.application.validators.CepValidator;
import com.wipro.freteapi.domain.frete.model.Frete;
import com.wipro.freteapi.domain.frete.model.ViaCep;

@Service
public class FreteService {
	
	private RestTemplate restTemplate;
	
	@Autowired
	private FreteBuilderService freteBuilderService;
	
	@Value("${viacep.baseurl}")
	private String viaCepUrl;
	
	@Value("${viacep.defaultresponse}")
	private String defaultFormat;
	
	public FreteService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	/**
	 * Generate Frete object to response of search of CEP.
	 * 
	 * @return frete class.
	 * @throws CepException if CEP format is invalid.
	 * @throws NotFoundException if CEP is valid, but not found.
	 * */
	public Frete consultFretePriceByCep(String cep, String responseFormat)
			throws CepException, NotFoundException, JsonMappingException, JsonProcessingException {
		String newCep = CepUtil.removeMaskAndChars(cep);
		
		if(CepValidator.isFormatValid(newCep)) {
			//Defines what is the response type: XML or JSON.
			String format = responseFormat == null
					         ? defaultFormat
					         : responseFormat;
			
			//Config URL and parameters to request
			String url = viaCepUrl + newCep + "/" + format;
			
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML));
			
			HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
			
			try {
				//Request
				String result = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class).getBody();
				
				//Treatment of response
				ViaCep viaCep = null;
				
				if (result != null) {
					//Converts string response (XML or JSON) to class Java
					if(ResponseFormat.XML.getFormat().equals(responseFormat)) {
						XmlMapper xmlMapper = new XmlMapper();
						viaCep = xmlMapper.readValue(result, ViaCep.class);
					
					} else if(ResponseFormat.JSON.getFormat().equals(responseFormat)) {
						Gson gson = new Gson();
						viaCep = gson.fromJson(result, ViaCep.class);
					}
					
					//Converts from class ViaCep to class Frete
					Frete frete = this.freteBuilderService.buildToRead(viaCep);
					
					return frete;
					
				} else {
					throw new NotFoundException("CEP not found.");
				}
			} catch(RestClientException e) {
				throw new CepException("CEP format is invalid.");
			}
		}
		
		throw new CepException("CEP format is invalid.");
	}
}
