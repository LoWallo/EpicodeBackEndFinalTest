package it.epicode.beservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.beservice.model.StatoFattura;
import it.epicode.beservice.repo.StatoFatturaRepository;

@Service
public class StatoFatturaService {
	
	@Autowired
	StatoFatturaRepository statoFattRepo;

	public StatoFattura findByNome(String stato) {
		return statoFattRepo.findByNome(stato);
	}
	
	public void save(String s) {
		statoFattRepo.save(new StatoFattura(s));
	}
	
}
