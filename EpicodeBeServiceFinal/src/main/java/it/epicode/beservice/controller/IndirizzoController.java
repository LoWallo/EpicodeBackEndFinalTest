package it.epicode.beservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.beservice.model.Indirizzo;
import it.epicode.beservice.service.IndirizzoService;

@RestController
@RequestMapping("/indirizzocontroller")
public class IndirizzoController {
	
	@Autowired
	IndirizzoService indirizzoServ;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveAddress(@RequestBody Indirizzo i) {
		indirizzoServ.save(i);
		return new ResponseEntity<String>("Indirizzo salvato correttamente", new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/delete")
	public ResponseEntity<String> deleteAddress(@RequestParam Long id) {
		indirizzoServ.deleteById(id);
		return new ResponseEntity<String>("Indirizzo eliminato correttamente", new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<String> updateAddress(@RequestBody Indirizzo i) {
		indirizzoServ.myUpdateAddress(i);
		return new ResponseEntity<String>("Indirizzo aggiornato correttamente", new HttpHeaders(), HttpStatus.OK);
	}
}
