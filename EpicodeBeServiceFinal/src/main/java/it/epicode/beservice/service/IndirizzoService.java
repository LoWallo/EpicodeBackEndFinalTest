package it.epicode.beservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.beservice.model.Indirizzo;
import it.epicode.beservice.repo.IndirizzoRepository;

@Service
public class IndirizzoService {
	
	@Autowired
	IndirizzoRepository indirizzoRepo;
	
	public Page<Indirizzo> myFindAllUsersPageable(Pageable pageable) {
		return indirizzoRepo.findAll(pageable);
	}
	
	public void save(Indirizzo ind) {
		indirizzoRepo.save(ind);
	}

	public void deleteById(Long id) {
		indirizzoRepo.deleteById(id);
	}
	
	public Indirizzo getById(Long id) {
		return indirizzoRepo.getById(id);
	}
	
	public Indirizzo getByVia(String s) {
		return indirizzoRepo.getByVia(s);
	}
	
	public void myUpdateAddress(Indirizzo i) {
		Indirizzo result = indirizzoRepo.getById(i.getId());
		result.setCap(i.getCap());
		result.setCivico(i.getCivico());
		result.setComune(i.getComune());
		result.setLocalita(i.getLocalita());
		result.setVia(i.getVia());
		indirizzoRepo.save(result);
	}
}
