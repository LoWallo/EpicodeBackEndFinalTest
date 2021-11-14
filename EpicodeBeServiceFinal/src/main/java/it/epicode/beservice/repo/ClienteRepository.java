package it.epicode.beservice.repo;

import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import it.epicode.beservice.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	public Page<Cliente> findAll(Pageable pageable);
	
	public Page<Cliente> findByFatturatoAnnuale(Long fatturatoAnnuale, Pageable pageable);
	
	public Page<Cliente> findByDataInserimento(LocalDate dataInserimento, Pageable pageable);
	
	public Page<Cliente> findByDataUltimoContatto(LocalDate dataUltimoContatto, Pageable pageable);
	
	public Page<Cliente> findByRagioneSocialeContains(String string, Pageable pageable);

	public Cliente findByRagioneSociale(String ragioneSociale);


}
