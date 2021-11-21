package it.epicode.beservice.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import it.epicode.beservice.model.ERoles;
import it.epicode.beservice.model.Role;
import it.epicode.beservice.model.User;
import it.epicode.beservice.repo.RoleRepository;
import it.epicode.beservice.repo.UserRepository;
import it.epicode.beservice.security.controller.JwtUtils;
import it.epicode.beservice.security.controller.LoginRequest;
import it.epicode.beservice.security.controller.LoginResponse;
import it.epicode.beservice.security.controller.SignupRequest;
import it.epicode.beservice.security.controller.SignupResponse;
import it.epicode.beservice.security.service.UserDetailsImpl;
import it.epicode.beservice.service.UserService;

@RestController
@RequestMapping("/usercontroller")
public class UserController {

	@Autowired
	UserService userServ;

	@GetMapping("/getbyid")
	public User GetById(@RequestParam Long Id) {
		return userServ.getById(Id);
	}

	@GetMapping("/findall")
	public List<User> FindAll() {
		return userServ.myFindAllUsers();
	}

	@GetMapping("/findbyid")
	public Optional<User> FindUserById(@RequestParam Long myId) {
		return userServ.myFindUserById(myId);
	}

	@PostMapping("/saveuserpost")
	public void save(@RequestBody User u) {
		userServ.save(u);
	}

	@GetMapping("/deletebyid")
	public void deleteById(Long id) {
		userServ.deleteById(id);
	}

	@GetMapping("/findbyusername")
	public Optional<User> findByUsername(String un) {
		return userServ.findByUsername(un);
	}

	// Paginazione
	@GetMapping(value = "/mygetalluserspage", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<User>> myGetAllUsersPage(Pageable pageable) {
		Page<User> findAll = userServ.myFindAllUsersPageable(pageable);
		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/mygetalluserssortbyname", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> myGetAllusersSortByName() {
		return userServ.myFindAllUsersSorted();
	}

	@GetMapping(value = "/mygetalluserspagesize", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> myGetAllUsersPageSize(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size) {
		Page<User> users = userServ.myFindAllUsersPageSize(page, size);
		Map<String, Object> myResponse = new HashMap<>();
		myResponse.put("users", users);
		return myResponse;
	}

	@GetMapping(value = "/mygetalluserspagesizesort", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> myGetAllUserPageSizeSort(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "2") Integer size, @RequestParam(defaultValue = "id") String sort) {
		List<User> list = userServ.myFindAllUsersPageSizeSort(page, size, sort);
		return new ResponseEntity<List<User>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserRepository userRepo;
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	RoleRepository roleRepo;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		// Usa l'AuthenticationManager per autenticare i parametri della request
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		// Ottiene i privilegi dell'utente
		authentication.getAuthorities();

		// Ottiene il SecurityContext
		SecurityContextHolder.getContext().setAuthentication(authentication);
		// Genera il token
		String jwt = jwtUtils.generateJwtToken(authentication);

		// getPrincipal(), ottiene i dati dell'utente
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		// Restituisce la response
		return ResponseEntity.ok(new LoginResponse(jwt, userDetails.getId(), userDetails.getUsername(),
				userDetails.getEmail(), roles, userDetails.getExpirationTime()));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
		// Verifica l'esistenza di Username e Email già registrate
		if (userRepo.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new SignupResponse("Errore: Username già in uso!"));
		}
		if (userRepo.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new SignupResponse("Errore: Email già in uso!"));
		}
		// Crea un nuovo user codificando la password
		User user = new User(signUpRequest.getUsername(), signUpRequest.getNome(), signUpRequest.getCognome(),
				encoder.encode(signUpRequest.getPassword()), signUpRequest.getEmail());
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		// Verifica l'esistenza dei Role
		if (strRoles == null) {
			Role userRole = roleRepo.findByRoleType(ERoles.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Errore: Role non trovato!"));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepo.findByRoleType(ERoles.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Errore: Role non trovato!"));
					roles.add(adminRole);
					break;
				default:
					Role userRole = roleRepo.findByRoleType(ERoles.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Errore: Role non trovato!"));
					roles.add(userRole);
				}
			});
		}
		user.setRoles(roles);
		userRepo.save(user);
		return ResponseEntity.ok(new SignupResponse("User registrato con successo!"));
	}
	

	@PostMapping(value = "/loginhtml", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ModelAndView loginHtml(LoginRequest loginRequest) {
		// Usa l'AuthenticationManager per autenticare i parametri della request
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		// Ottiene i privilegi dell'utente
		authentication.getAuthorities();
		// Ottiene il SecurityContext
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		// getPrincipal(), ottiene i dati dell'utente
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return new ModelAndView("userlogged");
	}

	@PostMapping(value = "/signuphtml", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ModelAndView registerUserThymeleaf(SignupRequest signUpRequest) {
		if (userRepo.existsByUsername(signUpRequest.getUsername())) {
			return new ModelAndView("alreadyusedusername");

		}
		if (userRepo.existsByEmail(signUpRequest.getEmail())) {
			return new ModelAndView("alreadyusedemail");

		}
		User user = new User(signUpRequest.getUsername(), signUpRequest.getNome(), 
				signUpRequest.getCognome(), encoder.encode(signUpRequest.getPassword()), 
				signUpRequest.getEmail());
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
			Role userRole = roleRepo.findByRoleType(ERoles.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Errore: Role non trovato!"));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepo.findByRoleType(ERoles.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Errore: Role non trovato!"));
					roles.add(adminRole);
					break;
				default:
					Role userRole = roleRepo.findByRoleType(ERoles.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Errore: Role non trovato!"));
					roles.add(userRole);
				}
			});
		}
		user.setRoles(roles);
		userRepo.save(user);
		return new ModelAndView("userregistered");

	}
}
