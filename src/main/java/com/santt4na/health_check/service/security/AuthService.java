package com.santt4na.health_check.service.security;

import com.santt4na.health_check.Startup;
import com.santt4na.health_check.dto.doctorDTO.DoctorRequestDTO;
import com.santt4na.health_check.dto.patientDTO.PatientRequestDTO;
import com.santt4na.health_check.dto.securityDTO.AccountCredentialsDTO;
import com.santt4na.health_check.dto.securityDTO.TokenDTO;
import com.santt4na.health_check.entity.Doctor;
import com.santt4na.health_check.entity.security.Permission;
import com.santt4na.health_check.entity.security.User;
import com.santt4na.health_check.exception.RequiredObjectIsNullException;
import com.santt4na.health_check.mapper.DoctorMapper;
import com.santt4na.health_check.mapper.UserMapper;
import com.santt4na.health_check.repository.DoctorRepository;
import com.santt4na.health_check.repository.PermissionsRepository;
import com.santt4na.health_check.repository.UserRepository;
import com.santt4na.health_check.security.jwt.JwtTokenProvider;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class AuthService {
	
	private static final Logger logger = LoggerFactory.getLogger(Startup.class);
	private static final Logger auditLogger = LoggerFactory.getLogger("audit");
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private PermissionsRepository repositoryPermission;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private DoctorMapper doctorMapper;
	
	public ResponseEntity<TokenDTO> signIn(AccountCredentialsDTO credentials) {
		authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				credentials.getUsername(),
				credentials.getPassword()
			)
		);
		
		var user = repository.findByUsername(credentials.getUsername());
		if (user == null) {
			throw new UsernameNotFoundException("Username " + credentials.getUsername() + " not found!");
		}
		
		var token = tokenProvider.createAccessToken(
			credentials.getUsername(),
			user.getRoles()
		);
		return ResponseEntity.ok(token);
	}
	
	public ResponseEntity<TokenDTO> refreshToken(String username, String refreshToken) {
		var user = repository.findByUsername(username);
		TokenDTO token;
		if (user != null) {
			token = tokenProvider.refreshToken(refreshToken);
		} else {
			throw new UsernameNotFoundException("Username " + username + " not found!");
		}
		return ResponseEntity.ok(token);
	}
	
	@Transactional
	public AccountCredentialsDTO registerDoctor(AccountCredentialsDTO user, DoctorRequestDTO doctor) {
		
		logger.info("Starting Create one new User Doctor");
		auditLogger.info("Creating one new User!");
		
		if (user == null) throw new RequiredObjectIsNullException();
		
		if (repository.existsByUserName(user.getUsername())) {
			throw new RuntimeException("Username already exists");
		}
		
		User newUser = userMapper.toEntity(user);
		
		newUser.setPassword(generateHashedPassword(user.getPassword()));
		
		newUser.setAccountNonExpired(true);
		newUser.setAccountNonLocked(true);
		newUser.setCredentialsNonExpired(true);
		newUser.setEnabled(true);
		
		Permission doctorPermission = repositoryPermission.findByDescription("DOCTOR")
			.orElseThrow(() -> new RuntimeException("Permission 'DOCTOR' not found"));
		
		newUser.setPermissions(List.of(doctorPermission));
		
		repository.save(newUser);
		
		Doctor newDoctor = doctorMapper.toEntity(doctor);
		newDoctor.setUser(newUser);
		
		doctorRepository.save(newDoctor);
		return userMapper.toDTO(newUser);
	}
	
	public AccountCredentialsDTO registerPatient(AccountCredentialsDTO user, PatientRequestDTO patient) {
		
		if (user == null) throw new RequiredObjectIsNullException();
		
		logger.info("Creating one new User!");
		var entity = new User();
		entity.setFullName(user.getFullname());
		entity.setUserName(user.getUsername());
		entity.setPassword(generateHashedPassword(user.getPassword()));
		entity.setAccountNonExpired(true);
		entity.setAccountNonLocked(true);
		entity.setCredentialsNonExpired(true);
		entity.setEnabled(true);
		
		Permission newPermission =  new Permission();
		newPermission.setDescription("DOCTOR");
		
		List<Permission> permissions = new ArrayList<>();
		permissions.add(newPermission);
		entity.setPermissions(permissions);
		
		var dto = repository.save(entity);
		return new AccountCredentialsDTO(dto.getUsername(), dto.getPassword(), dto.getFullName());
	}
	
	private String generateHashedPassword(String password) {
		
		PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder(
			"", 8, 185000,
			Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
		
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put("pbkdf2", pbkdf2Encoder);
		DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
		
		passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);
		return passwordEncoder.encode(password);
	}
}