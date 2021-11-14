package it.epicode.beservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.beservice.model.Indirizzo;

public interface IndirizzoRepository extends JpaRepository<Indirizzo, Long>{

	public Indirizzo getById(Long id);
	
	public Indirizzo getByVia(String s);

	public Indirizzo findByVia(String indirizzoSedeLegale);
	
}
