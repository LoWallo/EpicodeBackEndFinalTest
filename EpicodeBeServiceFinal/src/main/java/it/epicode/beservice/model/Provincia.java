package it.epicode.beservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "province")
public class Provincia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String sigla;
	
	
	public Provincia(String nome, String sigla) {
		super();
		this.nome = nome;
		this.sigla = sigla;
	}
	
	public Provincia() {
	}
}
//Provincia 
//-nome
//-sigla