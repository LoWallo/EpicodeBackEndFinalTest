package it.epicode.beservice.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import it.epicode.beservice.model.Cliente;
import it.epicode.beservice.model.TipoCliente;
import it.epicode.beservice.service.ClienteService;

@RestController
@RequestMapping("/clientecontroller")
public class ClienteController {

	@Autowired
	ClienteService clienteServ;
	
	@GetMapping(value = "/clientssortedbynameview", produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView myGetAllClientsPageSizeSortByNameView(
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size,
			@RequestParam(defaultValue = "ragioneSociale") String sort) {
		List<Cliente> list = clienteServ.myFindAllClientiPageSizeSort(page, size, sort);
		return new ModelAndView("listaclienti").addObject("clienti", list);
	}
	@GetMapping(value = "/clientssortedbyearningsview", produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView myGetAllClientsPageSizeSortByEarningsView(
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size,
			@RequestParam(defaultValue = "fatturatoAnnuale") String sort) {
		List<Cliente> list = clienteServ.myFindAllClientiPageSizeSort(page, size, sort);
		return new ModelAndView("listaclienti").addObject("clienti", list);
	}
	@GetMapping(value = "/clientssortedbyentrydateview", produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView myGetAllClientsPageSizeSortByEntryDateView(
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size,
			@RequestParam(defaultValue = "dataInserimento") String sort) {
		List<Cliente> list = clienteServ.myFindAllClientiPageSizeSort(page, size, sort);
		return new ModelAndView("listaclienti").addObject("clienti", list);
	}
	@GetMapping(value = "/clientssortedbylastcontactdateview", produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView myGetAllClientsPageSizeSortByLastContactDateView(
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size,
			@RequestParam(defaultValue = "dataUltimoContatto") String sort) {
		List<Cliente> list = clienteServ.myFindAllClientiPageSizeSort(page, size, sort);
		return new ModelAndView("listaclienti").addObject("clienti", list);
	}
	@GetMapping("/orderbyprovinciasedelegaleview")
	public ModelAndView myGetAllClientePageSizeSortByProvinciaView(
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size,
			@RequestParam(defaultValue = "indirizzoSedeLegale.comune.provincia") String sort) {
		List<Cliente> list = clienteServ.myFindAllClientiPageSizeSort(page, size, sort);
		return new ModelAndView("listaclienti").addObject("clienti", list);
	}
	
	
	
	

	@GetMapping(value = "/clientssortedbyname", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cliente>> myGetAllClientsPageSizeSortByName(
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "2") Integer size,
			@RequestParam(defaultValue = "ragioneSociale") String sort) {
		List<Cliente> list = clienteServ.myFindAllClientiPageSizeSort(page, size, sort);
		return new ResponseEntity<List<Cliente>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping(value = "/clientssortedbyearnings", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cliente>> myGetAllClientsPageSizeSortByEarnings(
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "3") Integer size,
			@RequestParam(defaultValue = "fatturatoAnnuale") String sort) {
		List<Cliente> list = clienteServ.myFindAllClientiPageSizeSort(page, size, sort);
		return new ResponseEntity<List<Cliente>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping(value = "/clientssortedbyentrydate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cliente>> myGetAllClientsPageSizeSortByEntryDate(
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "3") Integer size,
			@RequestParam(defaultValue = "dataInserimento") String sort) {
		List<Cliente> list = clienteServ.myFindAllClientiPageSizeSort(page, size, sort);
		return new ResponseEntity<List<Cliente>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping(value = "/clientssortedbylastcontactdate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cliente>> myGetAllClientsPageSizeSortByLastContactDate(
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "3") Integer size,
			@RequestParam(defaultValue = "dataUltimoContatto") String sort) {
		List<Cliente> list = clienteServ.myFindAllClientiPageSizeSort(page, size, sort);
		return new ResponseEntity<List<Cliente>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/orderbyprovinciasedelegale")
	public ResponseEntity<List<Cliente>> myGetAllClientePageSizeSortByProvincia(
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "3") Integer size,
			@RequestParam(defaultValue = "indirizzoSedeLegale.comune.provincia") String sort) {
		List<Cliente> list = clienteServ.myFindAllClientiPageSizeSort(page, size, sort);
		return new ResponseEntity<List<Cliente>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/saveclient")
	public ResponseEntity<String> saveClient(@RequestBody Cliente c) {
		clienteServ.mySaveClient(c);
		return new ResponseEntity<String>("Cliente " + c.getRagioneSociale() + " salvato correttamente",
				new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/deleteclient")
	public ResponseEntity<String> deleteClient(@RequestBody Long id) {
		clienteServ.myClientDelete(id);
		return new ResponseEntity<String>("Cliente eliminato correttamente", new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/updatecliente")
	public void updateClient(@RequestBody Cliente c) {
		clienteServ.updateClient(c);
	}

	@GetMapping("/findbyearnings")
	public Page<Cliente> findByEarnings(@RequestParam Long l, 
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "3") Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		return clienteServ.myFindByEarnings(l, pageable);
	}

	@GetMapping("/findbyentrydate")
	public Page<Cliente> findByEntryDate(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate l, 
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "3") Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		return clienteServ.myFindByEntryDate(l, pageable);
	}

	@GetMapping("/findbylastaccessdate")
	public Page<Cliente> findByLastAccessDate(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate l, 
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "3") Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		return clienteServ.myFindByLastAccessDate(l, pageable);
	}

	@GetMapping("/findbypartialname")
	public Page<Cliente> findByPartialName(@RequestParam String s, 
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "3") Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		return clienteServ.myFindByPartialName(s, pageable);
	}
	
	
	
	
	@GetMapping("/findbyearningsview")
	public ModelAndView findByEarningsView(@RequestParam Long fatturato, @RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "3") Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Cliente> p = clienteServ.myFindByEarnings(fatturato, pageable);
		return new ModelAndView("listaclienti").addObject("clienti", p.getContent());
	}
	@GetMapping("/findbyentrydateview")
	public ModelAndView findByEntryDateView(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate l, 
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "3") Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Cliente> p = clienteServ.myFindByEntryDate(l, pageable);
		return new ModelAndView("listaclienti").addObject("clienti", p.getContent());
	}
	@GetMapping("/findbylastaccessdateview")
	public ModelAndView findByLastAccessDateView(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate l, 
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "3") Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Cliente> p = clienteServ.myFindByLastAccessDate(l, pageable);
		return new ModelAndView("listaclienti").addObject("clienti", p.getContent());
	}
	@GetMapping("/findbypartialnameview")
	public ModelAndView findByPartialNameView(@RequestParam String s, 
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "3") Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Cliente> p = clienteServ.myFindByPartialName(s, pageable);
		return new ModelAndView("listaclienti").addObject("clienti", p.getContent());
	}
	
	@GetMapping("/saveclientget")
	public ModelAndView saveNewClient(@RequestParam String ragioneSociale, String partitaIva,
			TipoCliente tipoCliente, String email, String pec, String telefono, String nomeContatto,
			String cognomeContatto, String telefonoContatto, String emailContatto, String indirizzoSedeOperativa,
			String indirizzoSedeLegale, @DateTimeFormat(iso = ISO.DATE) LocalDate dataInserimento,
			@DateTimeFormat(iso = ISO.DATE) LocalDate dataUltimoContatto, Long fatturatoAnnuale) {
		clienteServ.mySaveClient2(ragioneSociale, partitaIva, tipoCliente, email, pec, telefono, nomeContatto,
				cognomeContatto, telefonoContatto, emailContatto, indirizzoSedeOperativa, indirizzoSedeLegale,
				dataInserimento, dataUltimoContatto, fatturatoAnnuale);
		return new ModelAndView("clientesalvato");
	}
	
	@GetMapping(value = "/modificaclientetemplate/{id}")
	public ModelAndView updateClienteTemplate(@PathVariable Long id, @RequestParam String ragioneSociale, String partitaIva, TipoCliente tipoCliente,
			String email, String pec, String telefono, String nomeContatto, String cognomeContatto, String telefonoContatto,
			String emailContatto, String indirizzoSedeOperativa,String indirizzoSedeLegale, @DateTimeFormat(iso = ISO.DATE)LocalDate dataInserimento,
			@DateTimeFormat(iso = ISO.DATE) LocalDate dataUltimoContatto,Long fatturatoAnnuale) {
		clienteServ.updateCliente2( id,ragioneSociale, partitaIva,tipoCliente, email,pec,telefono, nomeContatto,cognomeContatto,
				telefonoContatto,emailContatto, indirizzoSedeOperativa,indirizzoSedeLegale,dataInserimento,dataUltimoContatto,fatturatoAnnuale);
		return new ModelAndView("responseclient","stato","MODIFICATO!");
	}

}
