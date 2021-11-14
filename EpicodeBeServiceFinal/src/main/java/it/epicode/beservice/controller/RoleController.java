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

import it.epicode.beservice.model.Role;
import it.epicode.beservice.service.RoleService;

@RestController
@RequestMapping("/rolecontroller")
public class RoleController {
	
	@Autowired
	RoleService roleServ;
	
	@GetMapping("/findall")
	public List<Role> findAll() {
		return roleServ.findAll();
	}
	
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Role c) {
		roleServ.save(c);
		return new ResponseEntity<String>("Ruolo salvato correttamente", new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/delete")
	public ResponseEntity<String> delete(@RequestParam Long id) {
		roleServ.deleteById(id);
		return new ResponseEntity<String>("Ruolo eliminato correttamente", new HttpHeaders(), HttpStatus.OK);
	} 
}
