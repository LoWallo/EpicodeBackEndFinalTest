package it.epicode.beservice.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "clienti")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String ragioneSociale;
	@Column(unique = true, nullable = false)
	private String partitaIva;
	@Enumerated(EnumType.STRING)
	private TipoCliente tipoCliente;
	@Email
	private String email;
	@Email
	@Column(unique = true, nullable = false)
	private String pec;
	private String telefono;
	private String nomeContatto;
	private String cognomeContatto;
	private String telefonoContatto;
	@Email
	private String emailContatto;
	@OneToOne
	private Indirizzo indirizzoSedeOperativa;
	@OneToOne
	private Indirizzo indirizzoSedeLegale;
	private LocalDate dataInserimento;
	private LocalDate dataUltimoContatto;
	private Long fatturatoAnnuale;
	
	
	
	public Cliente(String ragioneSociale, String partitaIva, TipoCliente tipoCliente, @Email String email,
			@Email String pec, String telefono, String nomeContatto, String cognomeContatto, String telefonoContatto,
			@Email String emailContatto, Indirizzo indirizzoSedeOperativa, Indirizzo indirizzoSedeLegale,
			LocalDate dataInserimento, LocalDate dataUltimoContatto, Long fatturatoAnnuale) {
		this.ragioneSociale = ragioneSociale;
		this.partitaIva = partitaIva;
		this.tipoCliente = tipoCliente;
		this.email = email;
		this.pec = pec;
		this.telefono = telefono;
		this.nomeContatto = nomeContatto;
		this.cognomeContatto = cognomeContatto;
		this.telefonoContatto = telefonoContatto;
		this.emailContatto = emailContatto;
		this.indirizzoSedeOperativa = indirizzoSedeOperativa;
		this.indirizzoSedeLegale = indirizzoSedeLegale;
		this.dataInserimento = dataInserimento;
		this.dataUltimoContatto = dataUltimoContatto;
		this.fatturatoAnnuale = fatturatoAnnuale;
	}
}
//Cliente
//-ragioneSociale
//-partitaIva
//-tipoCliente
//-email
//-pec
//-telefono
//-nomeContatto
//-cognomeContatto
//-telefonoContatto
//-emailContatto
//-indirizzoSedeOperativa
//-indirizzoSedeLegale
//-dataInserimento
//-dataUltimoContatto
//-fatturatoAnnuale