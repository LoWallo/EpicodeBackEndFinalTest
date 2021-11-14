package it.epicode.beservice.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import it.epicode.beservice.model.Fattura;
import it.epicode.beservice.model.TipoCliente;
import it.epicode.beservice.service.FatturaService;

@RestController
@RequestMapping("/fatturacontroller")
public class FatturaController {

	@Autowired
	FatturaService fatturaServ;
	
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Fattura f) {
		fatturaServ.save(f);
		return new ResponseEntity<String>("Fattura salvato correttamente", new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/delete")
	public ResponseEntity<String> delete(@RequestParam Long id) {
		fatturaServ.deleteById(id);
		return new ResponseEntity<String>("Fattura eliminato correttamente", new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/update")
	public void update(@RequestBody Fattura f) {
		fatturaServ.update(f);
	}
	
	@GetMapping("/findbyclient")
	public Page<Fattura> findByClient(@RequestParam Long id,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "3") Integer size) {
		return fatturaServ.findByClient(id, page, size);
	}
	
	@GetMapping("/findbystato")
	public Page<Fattura> findByStato(@RequestParam String stato,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "3") Integer size) {
		return fatturaServ.findByStato(stato, page, size);
	}
	
	@GetMapping("/findbydate")
	public Page<Fattura> findByDate(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate date, 
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "3") Integer size) {
		return fatturaServ.findByDate(date, page, size);
	}
	
	@GetMapping("/findbyyear")
	public Page<Fattura> findByYear(@RequestParam Integer anno, 
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "3") Integer size) {
		return fatturaServ.findByYear(anno, page, size);
	}
	
	@GetMapping("/findbyamount")
	public Page<Fattura> findByImporto(@RequestParam Long v1,@RequestParam Long v2, 
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "3") Integer size) {
		return fatturaServ.findByImportoBetween(v1, v2, page, size);
	}
	
	
	
	
	@GetMapping("/findbyclientnameview")
	public ModelAndView findByClientNameView(@RequestParam String s,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "3") Integer size) {
		Page<Fattura> p = fatturaServ.findByClientString(s, page, size);
		return new ModelAndView("listafatture").addObject("fattura", p.getContent());
	}
	@GetMapping("/findbystatonameview")
	public ModelAndView findByStatoNameView(@RequestParam String stato,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "3") Integer size) {
		Page<Fattura> p = fatturaServ.findByStato(stato, page, size);
		return new ModelAndView("listafatture").addObject("fattura", p.getContent());
	}
	@GetMapping("/findbydateview")
	public ModelAndView findByDateView(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate date, 
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "3") Integer size) {
		Page<Fattura> p = fatturaServ.findByDate(date, page, size);
		return new ModelAndView("listafatture").addObject("fattura", p.getContent());
	}
	@GetMapping("/findbyyearview")
	public ModelAndView findByYearView(@RequestParam Integer anno, 
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "3") Integer size) {
		Page<Fattura> p = fatturaServ.findByYear(anno, page, size);
		return new ModelAndView("listafatture").addObject("fattura", p.getContent());
	}
	@GetMapping("/findbyamountview")
	public ModelAndView findByImportoView(@RequestParam Long v1,@RequestParam Long v2, 
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "3") Integer size) {
		Page<Fattura> p = fatturaServ.findByImportoBetween(v1, v2, page, size);
		return new ModelAndView("listafatture").addObject("fattura", p.getContent());
	}
	
	
	
	@GetMapping("/saveget")
	public ModelAndView saveGet(@RequestParam String ragioneSociale, @DateTimeFormat(iso = ISO.DATE)
		LocalDate data, String numero, Integer anno, Long importo, String stato) {
		fatturaServ.saveFattura2(ragioneSociale, data, numero, anno, importo, stato);
		return new ModelAndView("fatturasalvata");
	}
}
