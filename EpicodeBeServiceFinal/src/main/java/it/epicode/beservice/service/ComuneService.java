package it.epicode.beservice.service;

import java.util.List;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.beservice.model.Comune;
import it.epicode.beservice.repo.ComuneRepository;
import it.epicode.beservice.repo.ProvinciaRepository;

@Service
public class ComuneService {
	
	@Autowired
	ComuneRepository comuneRepo;
	@Autowired
	ProvinciaRepository provinciaRepo;
	
	
	public List<Comune> findAll() {
		return comuneRepo.findAll();
	}
	
	public void save(Comune comune) {
		comuneRepo.save(comune);
	}
	
	public void deleteById(Long id) {
		comuneRepo.deleteById(id);
	}
	
	public Comune getById(Long id) {
		return comuneRepo.getById(id);
	}
	
	public void update(Comune c) {
		Comune result = comuneRepo.getById(c.getId());
		result.setNome(c.getNome());
		result.setProvincia(c.getProvincia());
		comuneRepo.save(result);
	}
	
	public void loadFromFile() {
		final int INDICE_PROVINCIA = 3;
		final int INDICE_NOME = 2;
		final String FILE = "src/main/resources/comuni-italiani.csv";
		try {
			BufferedReader br = new BufferedReader(new FileReader(FILE));
			String line = null;// variabile per leggere le righe del file
			int count = 0;
			while ((line = br.readLine()) != null) {
				count++;
				if (count != 1) {
					String[] parti = line.split(";");
					String nome = parti[INDICE_NOME];
					Comune comune = new Comune(nome, provinciaRepo.findByNomeContains(parti[INDICE_PROVINCIA]));
					comuneRepo.save(comune);
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

