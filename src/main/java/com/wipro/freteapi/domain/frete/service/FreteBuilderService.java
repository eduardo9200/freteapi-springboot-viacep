package com.wipro.freteapi.domain.frete.service;

import org.springframework.stereotype.Service;

import com.wipro.freteapi.application.exceptions.CepException;
import com.wipro.freteapi.application.exceptions.NotFoundException;
import com.wipro.freteapi.application.utils.FreteUtil;
import com.wipro.freteapi.domain.frete.model.Frete;
import com.wipro.freteapi.domain.frete.model.ViaCep;

@Service
public class FreteBuilderService {
	
	/**
	 * Converts ViaCep class in Frete class.
	 * 
	 * @return Frete class.
	 * @throws CepException if viaCep is {@code null}.
	 * @throws NotFoundException if viaCep error attribute is {@code true}, that indicates CEP not found.
	 * */
	public Frete buildToRead(ViaCep viaCep) throws CepException, NotFoundException {
		if(viaCep == null) {
			throw new CepException("Failure on conversion XML or JSON to ViaCep object");
		}
		
		if(viaCep.isErro()) {
			throw new NotFoundException("CEP not found");
		}
		
		Frete frete = new Frete();
		frete.setCep(viaCep.getCep());
		frete.setRua(viaCep.getLogradouro());
		frete.setComplemento(viaCep.getComplemento());
		frete.setBairro(viaCep.getBairro());
		frete.setCidade(viaCep.getLocalidade());
		frete.setEstado(viaCep.getUf());
		frete.setFrete(FreteUtil.fretePrice(viaCep.getUf()));
		
		return frete;
	}
}
