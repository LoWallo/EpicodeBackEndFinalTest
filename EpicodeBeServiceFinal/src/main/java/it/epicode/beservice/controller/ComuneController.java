package it.epicode.beservice.controller;

import java.util.List;

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

import it.epicode.beservice.model.Comune;
import it.epicode.beservice.service.ComuneService;

@RestController
@RequestMapping("/comunecontroller")
public class ComuneController {
	
	@Autowired
	ComuneService comuneServ;
	
	@GetMapping("/findall")
	public List<Comune> findAll() {
		return comuneServ.findAll();
	}
	
	@PostMapping("/save")
	public ResponseEntity<String> saveAddress(@RequestBody Comune c) {
		comuneServ.save(c);
		return new ResponseEntity<String>("Comune " + c.getNome() + " salvato correttamente", new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/delete")
	public ResponseEntity<String> deleteAddress(@RequestParam Long id) {
		comuneServ.deleteById(id);
		return new ResponseEntity<String>("Comune eliminato correttamente", new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/update")
	public void updateAddress(@RequestBody Comune c) {
		comuneServ.update(c);
	}
}
