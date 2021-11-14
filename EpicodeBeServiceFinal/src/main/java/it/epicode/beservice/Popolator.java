package it.epicode.beservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.epicode.beservice.model.ERoles;
import it.epicode.beservice.model.Role;
import it.epicode.beservice.repo.StatoFatturaRepository;
import it.epicode.beservice.service.ComuneService;
import it.epicode.beservice.service.IndirizzoService;
import it.epicode.beservice.service.ProvinciaService;
import it.epicode.beservice.service.RoleService;
import it.epicode.beservice.service.StatoFatturaService;

@Component
public class Popolator implements CommandLineRunner{

	@Autowired
	ProvinciaService provinciaServ;
	@Autowired
	ComuneService comuneServ;
	@Autowired
	StatoFatturaService statoFatturaServ;
	@Autowired
	RoleService roleServ;

	@Override
	public void run(String... args) throws Exception {
		
		//Caricamento file .csv
		provinciaServ.loadFromFile();
		comuneServ.loadFromFile();
		
		//Caricamento unici due stati disponibili per una fattura
		statoFatturaServ.save("pagata");
		statoFatturaServ.save("da pagare");
		
		//Caricamento unici due tipi di ruoli per gli user
		roleServ.save(new Role(ERoles.ROLE_ADMIN));
		roleServ.save(new Role(ERoles.ROLE_USER));
	}
	
	
}
