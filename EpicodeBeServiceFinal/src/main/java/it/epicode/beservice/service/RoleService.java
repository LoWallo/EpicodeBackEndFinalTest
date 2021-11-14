package it.epicode.beservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.beservice.model.Role;
import it.epicode.beservice.repo.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	RoleRepository roleRepo;
	
	public List<Role> findAll() {
		return roleRepo.findAll();
	}
	
	public void save(Role role) {
		roleRepo.save(role);
	}
	
	public void deleteById(Long id) {
		roleRepo.deleteById(id);
	}
	
	public Role getById(Long id) {
		return roleRepo.getById(id);
	}
}
