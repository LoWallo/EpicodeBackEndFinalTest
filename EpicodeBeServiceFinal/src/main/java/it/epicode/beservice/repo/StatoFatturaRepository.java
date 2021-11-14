package it.epicode.beservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.beservice.model.StatoFattura;

public interface StatoFatturaRepository extends JpaRepository<StatoFattura, Long>{
	
	public StatoFattura findByNome(String s);
}
