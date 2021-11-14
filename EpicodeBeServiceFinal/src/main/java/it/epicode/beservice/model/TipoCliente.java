package it.epicode.beservice.model;

import javax.persistence.Table;

@Table(name = "tipiCliente")
public enum TipoCliente {
	SRL,
	SPA,
	SAS,
	PA
}
