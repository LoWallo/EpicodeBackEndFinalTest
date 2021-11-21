package it.epicode.beservice.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import it.epicode.beservice.model.ERoles;
import it.epicode.beservice.model.Role;
import it.epicode.beservice.model.User;
import it.epicode.beservice.repo.RoleRepository;
import it.epicode.beservice.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	RoleRepository roleRepo;

	public List<User> myFindAllUsers() {
		return userRepo.findAll();
	}

	public Page<User> myFindAllUsersPageable(Pageable pageable) {
		return userRepo.findAll(pageable);
	}

	// Ordinamento
	public List<User> myFindAllUsersSorted() {
		return userRepo.findByOrderByNomeAsc();
	}

	public Optional<User> myFindUserById(Long myId) {
		return userRepo.findById(myId);
	}

	public void save(User u1) {
		userRepo.save(u1);
	}

	public void deleteById(Long id) {
		userRepo.deleteById(id);
	}

	public void update(User u) {
		User result = userRepo.getById(u.getId());
		result.setUsername(u.getUsername());
		result.setEmail(u.getEmail());
		result.setPassword(u.getPassword());
		result.setNome(u.getNome());
		result.setCognome(u.getCognome());
		result.setRoles(u.getRoles());
		userRepo.save(result);
	}

	public User getById(Long Id) {
		return userRepo.getById(Id);
	}

	public Optional<User> findByUsername(String un) {
		return userRepo.myFindUserByUsername(un);
	}

	public Page<User> myFindAllUsersPageSize(Integer page, Integer size) {
		Pageable paging = PageRequest.of(page, size);
		Page<User> pagedResult = userRepo.findAll(paging);
		if (pagedResult.hasContent()) {
			return pagedResult;
		} else {
			return null;
		}
	}

	// Paginazione e Ordinamento
	public List<User> myFindAllUsersPageSizeSort(Integer page, Integer size, String sort) {
		Pageable paging = PageRequest.of(page, size, Sort.by(sort));
		Page<User> pagedResult = userRepo.findAll(paging);
		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<User>();
		}
	}
	
	public void saveAdminPopolator(String username, String nome, String cognome, 
			String password, String email, Long idRole) {
		User admin = new User(username, nome, cognome, encoder.encode(password), email);
		Set<Role> roleSet = new HashSet<>();
		Role userRole = roleRepo.findByRoleType(ERoles.ROLE_ADMIN)
				.orElseThrow(() -> new RuntimeException("Errore: Role non trovato!"));
		roleSet.add(userRole);
		admin.setRoles(roleSet);
		userRepo.save(admin);
	}
}
