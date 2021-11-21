package it.epicode.beservice.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.beservice.model.Fattura;
import it.epicode.beservice.repo.ClienteRepository;
import it.epicode.beservice.repo.FatturaRepository;

@Service
public class FatturaService {
	
	@Autowired
	FatturaRepository fatturaRepo;
	@Autowired
	ClienteRepository clienteRepo;
	@Autowired
	StatoFatturaService statoFattServ;
	
	public List<Fattura> findAll() {
		return fatturaRepo.findAll();
	}

	public void save(Fattura f) {
		fatturaRepo.save(f);
	}
	
	public void saveInvoice2(String ragioneSociale, LocalDate data, String numero, Integer anno,
			Long importo, String stato) {
		fatturaRepo.save(new Fattura(clienteRepo.findByRagioneSociale(ragioneSociale),
				data,numero,anno,importo,statoFattServ.findByNome(stato)));
	}
	
	public void saveInvoicePopolator(Long idCliente, LocalDate data, String numero, Integer anno,
			Long importo, String stato) {
		fatturaRepo.save(new Fattura(clienteRepo.getById(idCliente),
				data,numero,anno,importo,statoFattServ.findByNome(stato)));
	}

	public void deleteById(Long id) {
		fatturaRepo.deleteById(id);
	}
	
	public Fattura getById(Long id) {
		return fatturaRepo.getById(id);
	}
	
	public void update(Fattura f) {
		Fattura result = fatturaRepo.getById(f.getId());
		result.setCliente(f.getCliente());
		result.setData(f.getData());
		result.setNumero(f.getNumero());
		result.setAnno(f.getAnno());
		result.setImporto(f.getImporto());
		result.setStato(f.getStato());
		fatturaRepo.save(result);
	}
	
	public Page<Fattura> findByClient(Long id, Integer page, Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		return fatturaRepo.findByClienteId(id, pageable);
	}
	
	public Page<Fattura> findByStato(String stato, Integer page, Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		return fatturaRepo.findByStatoNome(stato, pageable);
	}
	
	public Page<Fattura> findByDate(LocalDate data, Integer page, Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		return fatturaRepo.findByData(data, pageable);
	}
	
	public Page<Fattura> findByYear(Integer anno, Integer page, Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		return fatturaRepo.findByAnno(anno, pageable);
	}
	
	public Page<Fattura> findByImportoBetween(Long value1, Long value2, Integer page, Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		return fatturaRepo.findByImportoBetween(value1, value2, pageable);
	}

	public Page<Fattura> findByClientString(String s, Integer page, Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		return fatturaRepo.findByCliente(clienteRepo.findByRagioneSociale(s), pageable);
	}
}
//Deve essere possibile cercare le fatture per
//Cliente
//Stato
//Data
//Anno
//Range di importi