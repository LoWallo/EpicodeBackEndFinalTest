package it.epicode.beservice;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.epicode.beservice.model.ERoles;
import it.epicode.beservice.model.Role;
import it.epicode.beservice.model.TipoCliente;
import it.epicode.beservice.repo.RoleRepository;
import it.epicode.beservice.service.ClienteService;
import it.epicode.beservice.service.ComuneService;
import it.epicode.beservice.service.FatturaService;
import it.epicode.beservice.service.IndirizzoService;
import it.epicode.beservice.service.ProvinciaService;
import it.epicode.beservice.service.RoleService;
import it.epicode.beservice.service.StatoFatturaService;
import it.epicode.beservice.service.UserService;

@Component
public class Popolator implements CommandLineRunner{

	@Autowired
	ProvinciaService provinciaServ;
	@Autowired
	ComuneService comuneServ;
	@Autowired
	StatoFatturaService statoFatturaServ;
	@Autowired
	RoleRepository roleRepo;
	@Autowired
	RoleService roleServ;
	@Autowired
	ClienteService clienteServ;
	@Autowired
	IndirizzoService indirizzoServ;
	@Autowired
	FatturaService fatturaServ;
	@Autowired
	UserService userServ;

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
		
		//Caricamento database indirizzi
		indirizzoServ.saveAddressPopolator("Via Marsala", "10", "21020", "Belvedere", 1278l);
		indirizzoServ.saveAddressPopolator("Belvedere d'Ulisse", "23", "04016", "Belsito", 5253l);
		indirizzoServ.saveAddressPopolator("Vico Gallinaro", "10", "74024", "Uggiano Montefusco", 6458l);
		indirizzoServ.saveAddressPopolator("Piazza dei Mille", "4", "57023", "San Pietro in Palazzi", 4501l);
		indirizzoServ.saveAddressPopolator("Via dell'acquedotto", "20", "55051", "Bugliano", 4403l);
		indirizzoServ.saveAddressPopolator("Viale Italia", "104", "57128", "Ardenza", 4503l);
		
		//Popolamento database clienti
		clienteServ.saveClientPopolator("LEO PEN SRL", "03746700123", TipoCliente.SRL, 
				"leopen@gmail.com", "leopenpec@gmail.com", "336 5025814", "Paolo", "Pane", 
				"331 897055", "pbassi@gmail.com", 1l, 2l, LocalDate.of(2015, 6, 4), 
				LocalDate.of(2021, 11, 12), 560000l);
		clienteServ.saveClientPopolator("CEC SRL", "01788320495", TipoCliente.SRL, 
				"cec@gmail.com", "cecpec@gmail.com", "336 0528491", "Stefano", "Sereni", 
				"332 9806541", "ssereni@gmail.com", 6l, 5l, LocalDate.of(2012, 8, 1), 
				LocalDate.of(2021, 10, 30), 215000l);
		clienteServ.saveClientPopolator("CHAIN SPA", "01568415902", TipoCliente.SPA, 
				"chain@gmail.com", "chainpec@gmail.com", "336 0851904", "Luca", "Lapadula", 
				"338 9516250", "llapadula@gmail.com", 3l, 3l, LocalDate.of(2019, 4, 21), 
				LocalDate.of(2021, 11, 2), 1150000l);
		clienteServ.saveClientPopolator("TUCU SPA", "01635980451", TipoCliente.SPA, 
				"tucu@gmail.com", "tucupec@gmail.com", "334 5239870", "Andres", "Albrecht", 
				"329 5680478", "aalbrecht@gmail.com", 5l, 6l, LocalDate.of(2020, 1, 1), 
				LocalDate.of(2021, 11, 7), 4300000l);
		clienteServ.saveClientPopolator("TAPIOCA SAS", "01759623055", TipoCliente.SAS, 
				"tapioca@gmail.com", "tapiocapec@gmail.com", "331 6584866", "Brenno", "Bastianich", 
				"340 5963288", "bbastianich@gmail.com", 4l, 6l, LocalDate.of(2018, 7, 15), 
				LocalDate.of(2021, 11, 20), 280000l);
		clienteServ.saveClientPopolator("COMUNE DI CECINA", "00199700493", TipoCliente.PA, 
				"informazione@comune.cecina.li.it", "protocollo@cert.comune.cecina.li.it", "0586 611111", 
				"Lapo", "Lundini", "347 1945787", "llundini@gmail.com", 4l, 4l, LocalDate.of(2018, 7, 15), 
				LocalDate.of(2021, 11, 20), 280000l);
		
		//Popolamento database fatture
		fatturaServ.saveInvoicePopolator(1l, LocalDate.of(2019, 2, 20), "198A", 2019, 25000l, "pagata");
		fatturaServ.saveInvoicePopolator(1l, LocalDate.of(2021, 7, 9), "756", 2021, 9800l, "da pagare");
		fatturaServ.saveInvoicePopolator(1l, LocalDate.of(2021, 7, 21), "770", 2021, 6000l, "pagata");
		fatturaServ.saveInvoicePopolator(1l, LocalDate.of(2021, 10, 20), "1098", 2021, 4500l, "da pagare");
		
		fatturaServ.saveInvoicePopolator(2l, LocalDate.of(2019, 1, 31), "45", 2019, 3300l, "pagata");
		fatturaServ.saveInvoicePopolator(2l, LocalDate.of(2020, 4, 3), "410", 2020, 1220l, "pagata");
		fatturaServ.saveInvoicePopolator(2l, LocalDate.of(2020, 6, 9), "630", 2020, 6000l, "pagata");
		fatturaServ.saveInvoicePopolator(2l, LocalDate.of(2021, 5, 21), "480A", 2021, 30000l, "da pagare");
		
		fatturaServ.saveInvoicePopolator(3l, LocalDate.of(2019, 12, 3), "1032", 2019, 35000l, "pagata");
		fatturaServ.saveInvoicePopolator(3l, LocalDate.of(2019, 12, 18), "1105", 2019, 24500l, "pagata");
		fatturaServ.saveInvoicePopolator(3l, LocalDate.of(2021, 6, 30), "582F", 2021, 60000l, "da pagare");
		fatturaServ.saveInvoicePopolator(3l, LocalDate.of(2021, 10, 1), "942", 2021, 42000l, "da pagare");

		fatturaServ.saveInvoicePopolator(4l, LocalDate.of(2020, 3, 5), "345", 2020, 1300l, "pagata");
		fatturaServ.saveInvoicePopolator(4l, LocalDate.of(2020, 8, 2), "810", 2020, 3400l, "pagata");
		fatturaServ.saveInvoicePopolator(4l, LocalDate.of(2021, 1, 26), "59A", 2021, 7000l, "da pagare");
		fatturaServ.saveInvoicePopolator(4l, LocalDate.of(2021, 4, 28), "399A", 2021, 15000l, "pagata");
		
		fatturaServ.saveInvoicePopolator(5l, LocalDate.of(2019, 11, 8), "729F", 2019, 8000l, "pagata");
		fatturaServ.saveInvoicePopolator(5l, LocalDate.of(2020, 3, 20), "409", 2020, 9150l, "pagata");
		fatturaServ.saveInvoicePopolator(5l, LocalDate.of(2020, 12, 20), "1302", 2020, 60000l, "pagata");
		fatturaServ.saveInvoicePopolator(5l, LocalDate.of(2021, 11, 20), "884A", 2021, 48000l, "pagata");
		
		fatturaServ.saveInvoicePopolator(6l, LocalDate.of(2019, 9, 26), "PA-345", 2019, 34000l, "pagata");
		fatturaServ.saveInvoicePopolator(6l, LocalDate.of(2020, 5, 30), "PA-210", 2020, 6500l, "pagata");
		fatturaServ.saveInvoicePopolator(6l, LocalDate.of(2021, 9, 18), "PA-503", 2021, 9800l, "pagata");
		fatturaServ.saveInvoicePopolator(6l, LocalDate.of(2021, 11, 21), "PA-689", 2021, 88000l, "pagata");
		
		//Creazione di un Utente ##### L'Username è 'admin', la Password è 'password'
		userServ.saveAdminPopolator("admin", "Leonardo", "Vallini", "password", "pio@gmail.com", 1l);
		
	}
	
	
}
