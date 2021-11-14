package it.epicode.beservice.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.epicode.beservice.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE u.username=:un")
	public Optional<User> myFindUserByUsername(String un);

	public Page<User> findAll(Pageable pageable);

	/* Sort */
	// Formula: findBy + OrderBy + NomeColonna + Ordinamento(Asc/Desc)
	public List<User> findByOrderByNomeAsc();
	
	Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
