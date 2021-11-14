package it.epicode.beservice.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.beservice.model.Cliente;
import it.epicode.beservice.model.Fattura;

public interface FatturaRepository extends JpaRepository<Fattura, Long>{

	Page<Fattura> findByClienteId(Long Id, Pageable pageable);

	Page<Fattura> findByStatoNome(String stato, Pageable pageable);

	Page<Fattura> findByData(LocalDate data, Pageable pageable);

	Page<Fattura> findByAnno(Integer anno, Pageable pageable);

	Page<Fattura> findByImportoBetween(Long value1, Long value2, Pageable pageable);
	
	
	
	
	Page<Fattura> findByCliente(Cliente c, Pageable pageable);
	
}
