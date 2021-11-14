package it.epicode.beservice.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import it.epicode.beservice.security.StringAttributeConverter;
import it.epicode.beservice.service.RoleService;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	@Convert(converter = StringAttributeConverter.class)
	private String email;
	private String password;
	private String nome;
	private String cognome;
	private boolean active = true;
	@ManyToMany
	@JoinTable(name="user_roles",joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="roles_id"))
	protected Set<Role> roles = new HashSet<>();	
	
	public User(String username, String nome, String cognome, String password, String email) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
	}
	
}
//User
//-username
//-email
//-password
//-nome
//-cognome
//-roles