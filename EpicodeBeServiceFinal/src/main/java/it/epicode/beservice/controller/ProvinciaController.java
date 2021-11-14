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

import it.epicode.beservice.model.Provincia;
import it.epicode.beservice.service.ProvinciaService;

@RestController
@RequestMapping("/provinciacontroller")
public class ProvinciaController {

	@Autowired
	ProvinciaService provinciaServ;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveProvince(@RequestBody Provincia p) {
		provinciaServ.save(p);
		return new ResponseEntity<String>("Provincia salvata correttamente", new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/delete")
	public ResponseEntity<String> deleteProvince(@RequestParam Long id) {
		provinciaServ.deleteById(id);
		return new ResponseEntity<String>("Provincia eliminata correttamente", new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/update")
	public void updateProvince(@RequestBody Provincia p) {
		provinciaServ.myUpdateProvince(p);
	}
}
