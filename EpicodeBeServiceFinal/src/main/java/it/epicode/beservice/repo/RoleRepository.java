package it.epicode.beservice.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.beservice.model.ERoles;
import it.epicode.beservice.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Optional<Role> findByRoleType(ERoles roletype);
}
