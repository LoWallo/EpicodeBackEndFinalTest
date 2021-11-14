package it.epicode.beservice.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "fatture")
public class Fattura {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Cliente cliente;
	private LocalDate data;
	private String numero;
	private Integer anno;
	private Long importo;
	@OneToOne
	private StatoFattura stato;
	
	
	public Fattura(Cliente cliente, LocalDate data, String numero, Integer anno, Long importo, StatoFattura stato) {
		super();
		this.cliente = cliente;
		this.data = data;
		this.numero = numero;
		this.anno = anno;
		this.importo = importo;
		this.stato = stato;
	}
}
//Fattura
//-data
//-numero
//-anno
//-importo
//-stato