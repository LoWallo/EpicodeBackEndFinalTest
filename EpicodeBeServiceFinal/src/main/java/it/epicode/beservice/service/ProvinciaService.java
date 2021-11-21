package it.epicode.beservice.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.beservice.model.Provincia;
import it.epicode.beservice.repo.ProvinciaRepository;

@Service
public class ProvinciaService {
	@Autowired
	ProvinciaRepository provinciaRepo;

	public List<Provincia> findAll() {
		return provinciaRepo.findAll();
	}

	public void save(Provincia prov) {
		provinciaRepo.save(prov);
	}

	public void deleteById(Long id) {
		provinciaRepo.deleteById(id);
	}
	
	public void myUpdateProvince(Provincia p) {
		Provincia p2 = provinciaRepo.getById(p.getId());
		p2.setNome(p.getNome());
		p2.setSigla(p.getSigla());
		provinciaRepo.save(p2);
	}
	
	public Provincia getById(Long id) {
		return provinciaRepo.getById(id);
	}

	public void loadFromFile() {
		final int INDICE_SIGLA = 0;
		final int INDICE_NOME = 1;
		final String FILE = "src/main/resources/province-italiane.csv";
		try {
			BufferedReader br = new BufferedReader(new FileReader(FILE));
			String line = null;// variabile per leggere le righe del file
			int count = 0;
			while ((line = br.readLine()) != null) {
				count++;
				if (count != 1) {
					String[] parti = line.split(";");
					String nome = parti[INDICE_NOME];
					String sigla = parti[INDICE_SIGLA];
					Provincia provincia = new Provincia(nome, sigla);
					provinciaRepo.save(provincia);
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
