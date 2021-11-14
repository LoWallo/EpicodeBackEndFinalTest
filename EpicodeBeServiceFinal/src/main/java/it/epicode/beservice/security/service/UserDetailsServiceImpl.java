package it.epicode.beservice.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.epicode.beservice.model.User;
import it.epicode.beservice.repo.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	// @Transactional
	// Cerca l'utenete nel DB e ritorna l'implementazione di UserDetailsImpl o
	// un'eccezione
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.myFindUserByUsername(username);
		if (user.isPresent()) {
			return UserDetailsImpl.build(user.get());
		} else {
			throw new UsernameNotFoundException("User Not Found with username: " + username);
		}
	}

}