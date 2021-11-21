package it.epicode.beservice.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import it.epicode.beservice.model.Cliente;
import it.epicode.beservice.model.TipoCliente;
import it.epicode.beservice.repo.ClienteRepository;
import it.epicode.beservice.repo.IndirizzoRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepo;
	@Autowired
	IndirizzoRepository indirizzoRepo;

	public List<Cliente> myFindAllClientiPageSizeSort(Integer page, Integer size, String sort) {
		Pageable paging = PageRequest.of(page, size, Sort.by(sort));
		Page<Cliente> pagedResult = clienteRepo.findAll(paging);
		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<Cliente>();
		}
	}

	public List<Cliente> myFindAllClients(Integer page, Integer size) {
		Pageable paging = PageRequest.of(page, size);
		Page<Cliente> pagedResult = clienteRepo.findAll(paging);
		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<Cliente>();
		}
	}

	public void mySaveClient(Cliente cliente) {
		clienteRepo.save(cliente);
	}

	public void myClientDelete(Long id) {
		clienteRepo.deleteById(id);
	}

	public Page<Cliente> myFindByEarnings(Long l, Pageable pageable) {
		return clienteRepo.findByFatturatoAnnuale(l, pageable);
	}

	public Page<Cliente> myFindByEntryDate(LocalDate l, Pageable pageable) {
		return clienteRepo.findByDataInserimento(l, pageable);
	}

	public Page<Cliente> myFindByLastAccessDate(LocalDate l, Pageable pageable) {
		return clienteRepo.findByDataUltimoContatto(l, pageable);
	}

	public Page<Cliente> myFindByPartialName(String s, Pageable pageable) {
		return clienteRepo.findByRagioneSocialeContains(s, pageable);
	}

	public void updateClient(Cliente c) {
		Cliente clientFromDb = clienteRepo.getById(c.getId());
		clientFromDb.setCognomeContatto(c.getCognomeContatto());
		clientFromDb.setDataInserimento(c.getDataInserimento());
		clientFromDb.setDataUltimoContatto(c.getDataUltimoContatto());
		clientFromDb.setEmail(c.getEmail());
		clientFromDb.setEmailContatto(c.getEmailContatto());
		clientFromDb.setFatturatoAnnuale(c.getFatturatoAnnuale());
		clientFromDb.setIndirizzoSedeLegale(c.getIndirizzoSedeLegale());
		clientFromDb.setIndirizzoSedeOperativa(c.getIndirizzoSedeOperativa());
		clientFromDb.setNomeContatto(c.getNomeContatto());
		clientFromDb.setPartitaIva(c.getPartitaIva());
		clientFromDb.setPec(c.getPec());
		clientFromDb.setRagioneSociale(c.getRagioneSociale());
		clientFromDb.setTelefono(c.getTelefono());
		clientFromDb.setTelefonoContatto(c.getTelefonoContatto());
		clientFromDb.setTipoCliente(c.getTipoCliente());
		clienteRepo.save(clientFromDb);
	}

	public void mySaveClient2(String ragioneSociale, String partitaIva, TipoCliente tipoCliente, String email,
			String pec, String telefono, String nomeContatto, String cognomeContatto, String telefonoContatto,
			String emailContatto, String indirizzoSedeOperativa, String indirizzoSedeLegale, LocalDate dataInserimento,
			LocalDate dataUltimoContatto, Long fatturatoAnnuale) {
		clienteRepo.save(new Cliente(ragioneSociale, partitaIva, tipoCliente, email, pec, telefono, nomeContatto,
				cognomeContatto, telefonoContatto, emailContatto, indirizzoRepo.getByVia(indirizzoSedeOperativa),
				indirizzoRepo.getByVia(indirizzoSedeLegale), dataInserimento, dataUltimoContatto,
				fatturatoAnnuale));
	}
	
	public void saveClientPopolator(String ragioneSociale, String partitaIva, TipoCliente tipoCliente, String email,
			String pec, String telefono, String nomeContatto, String cognomeContatto, String telefonoContatto,
			String emailContatto, Long indirizzoSedeOperativa, Long indirizzoSedeLegale, LocalDate dataInserimento,
			LocalDate dataUltimoContatto, Long fatturatoAnnuale) {
		clienteRepo.save(new Cliente(ragioneSociale, partitaIva, tipoCliente, email, pec, telefono, nomeContatto,
				cognomeContatto, telefonoContatto, emailContatto, indirizzoRepo.getById(indirizzoSedeOperativa),
				indirizzoRepo.getById(indirizzoSedeLegale), dataInserimento, dataUltimoContatto,
				fatturatoAnnuale));
	}
	
	
	
	
	public void updateCliente2(Long id,String ragioneSociale, String partitaIva, TipoCliente tipoCliente,
			String email, String pec, String telefono, String nomeContatto, String cognomeContatto, String telefonoContatto,
			String emailContatto, String indirizzoSedeOperativa,String indirizzoSedeLegale, LocalDate dataInserimento,
			LocalDate dataUltimoContatto,Long fatturatoAnnuale) {
		Cliente clientFromDb = clienteRepo.getById(id);
		if(cognomeContatto!=null)
			clientFromDb.setCognomeContatto(cognomeContatto);
		if(dataInserimento!=null)
			clientFromDb.setDataInserimento(dataInserimento);
		if(dataUltimoContatto!=null)
			clientFromDb.setDataUltimoContatto(dataUltimoContatto);
		if(email!=null)
			clientFromDb.setEmail(email);
		if(emailContatto!=null)
			clientFromDb.setEmailContatto(emailContatto);
		if(fatturatoAnnuale!=null)
			clientFromDb.setFatturatoAnnuale(fatturatoAnnuale);
		if(indirizzoSedeLegale!=null)
			clientFromDb.setIndirizzoSedeLegale(indirizzoRepo.findByVia(indirizzoSedeLegale));
		if(indirizzoSedeOperativa!=null)
			clientFromDb.setIndirizzoSedeOperativa(indirizzoRepo.findByVia(indirizzoSedeOperativa));
		if(nomeContatto!=null)
			clientFromDb.setNomeContatto(nomeContatto);
		if(partitaIva!=null)
			clientFromDb.setPartitaIva(partitaIva);
		if(pec!=null)
			clientFromDb.setPec(pec);
		if(ragioneSociale!=null)
			clientFromDb.setRagioneSociale(ragioneSociale);
		if(telefono!=null)
			clientFromDb.setTelefono(telefono);
		if(telefonoContatto!=null)
			clientFromDb.setTelefonoContatto(telefonoContatto);
		if(tipoCliente!=null)
			clientFromDb.setTipoCliente(tipoCliente);
		clienteRepo.save(clientFromDb);
	}

}
