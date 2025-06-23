package com.santt4na.health_check.service.security;

import com.santt4na.health_check.Startup;
import com.santt4na.health_check.dto.doctorDTO.DoctorRequestDTO;
import com.santt4na.health_check.dto.patientDTO.PatientRequestDTO;
import com.santt4na.health_check.dto.securityDTO.AccountCredentialsDTO;
import com.santt4na.health_check.dto.securityDTO.TokenDTO;
import com.santt4na.health_check.entity.Doctor;
import com.santt4na.health_check.entity.Patient;
import com.santt4na.health_check.entity.security.Permission;
import com.santt4na.health_check.entity.security.User;
import com.santt4na.health_check.mapper.DoctorMapper;
import com.santt4na.health_check.mapper.PatientMapper;
import com.santt4na.health_check.mapper.UserMapper;
import com.santt4na.health_check.repository.DoctorRepository;
import com.santt4na.health_check.repository.PatientRepository;
import com.santt4na.health_check.repository.PermissionsRepository;
import com.santt4na.health_check.repository.UserRepository;
import com.santt4na.health_check.security.jwt.JwtTokenProvider;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthService {
	
	private static final Logger logger = LoggerFactory.getLogger(Startup.class);
	private static final Logger auditLogger = LoggerFactory.getLogger("audit");
	
	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider tokenProvider;
	private final UserRepository userRepository;
	private final DoctorRepository doctorRepository;
	private final PermissionsRepository repositoryPermission;
	private final UserMapper userMapper;
	private final DoctorMapper doctorMapper;
	private final PatientMapper patientMapper;
	private final PatientRepository patientRepository;
	private final PasswordEncoder passwordEncoder;
	
	public AuthService(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, UserRepository userRepository, DoctorRepository doctorRepository, PermissionsRepository repositoryPermission, UserMapper userMapper, DoctorMapper doctorMapper, PatientMapper patientMapper, PatientRepository patientRepository, PasswordEncoder passwordEncoder) {
		this.authenticationManager = authenticationManager;
		this.tokenProvider = tokenProvider;
		this.userRepository = userRepository;
		this.doctorRepository = doctorRepository;
		this.repositoryPermission = repositoryPermission;
		this.userMapper = userMapper;
		this.doctorMapper = doctorMapper;
		this.patientMapper = patientMapper;
		this.patientRepository = patientRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public ResponseEntity<TokenDTO> signIn(AccountCredentialsDTO credentials) {
		authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				credentials.getUserName(),
				credentials.getPassword()
			)
		);
		
		var user = userRepository.findByUsername(credentials.getUserName());
		if (user == null) {
			throw new UsernameNotFoundException("Username " + credentials.getUserName() + " not found!");
		}
		
		var token = tokenProvider.createAccessToken(
			credentials.getUserName(),
			user.getRoles()
		);
		return ResponseEntity.ok(token);
	}
	
	public ResponseEntity<TokenDTO> refreshToken(String username, String refreshToken) {
		var user = userRepository.findByUsername(username);
		TokenDTO token;
		if (user != null) {
			token = tokenProvider.refreshToken(refreshToken);
		} else {
			throw new UsernameNotFoundException("Username " + username + " not found!");
		}
		return ResponseEntity.ok(token);
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
	
	@Transactional
	public Map<String, Object> registerDoctor(AccountCredentialsDTO userDTO, DoctorRequestDTO doctorDTO){
		
		if (userDTO == null || doctorDTO == null) {
			throw new IllegalArgumentException("User and doctor data cannot be null");
		}
		
		if (StringUtils.isBlank(userDTO.getPassword())) {
			throw new IllegalArgumentException("Password cannot be null or empty");
		}
		
		if (userRepository.existsByUserName(userDTO.getUserName())) {
			throw new IllegalArgumentException("Username already exists");
		}
		
		if (doctorRepository.existsByMedicalLicense(doctorDTO.medicalLicense())) {
			throw new IllegalArgumentException("Medical license already registered");
		}
		
		User newUser = userMapper.toEntity(userDTO);
		
		newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		
		Permission doctorPermission = repositoryPermission.findByDescription("DOCTOR")
			.orElseGet(() -> createPermission("DOCTOR"));
		
		newUser.setPermissions(List.of(doctorPermission));
		
		User savedUser = userRepository.save(newUser);
		
		Doctor newDoctor = doctorMapper.toEntity(doctorDTO);
		
		newDoctor.setUser(savedUser);
		
		Doctor savedDoctor = doctorRepository.save(newDoctor);
		
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
			userDTO.getUserName(),
			userDTO.getPassword()
		);
		
		Authentication authentication = authenticationManager.authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		List<String> roles = authentication.getAuthorities().stream()
			.map(GrantedAuthority::getAuthority)
			.collect(Collectors.toList());
		
		TokenDTO tokenDto = tokenProvider.createAccessToken(
			authentication.getName(),
			roles
		);
		
		Map<String, Object> response = new HashMap<>();
		response.put("doctor", doctorMapper.toResponseDto(savedDoctor));
		response.put("token", tokenDto);
		
		return response;
	}
	
	@Transactional
	public Map<String, Object> registerPatient(AccountCredentialsDTO userDTO, PatientRequestDTO patientDTO){
		
		if (userDTO == null || patientDTO == null) {
			throw new IllegalArgumentException("User and patient data cannot be null");
		}
		
		if (StringUtils.isBlank(userDTO.getPassword())) {
			throw new IllegalArgumentException("Password cannot be null or empty");
		}
		
		if (userRepository.existsByUserName(userDTO.getUserName())) {
			throw new IllegalArgumentException("Username already exists");
		}
		
		User newUser = userMapper.toEntity(userDTO);
		
		newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		
		Permission patientPermission = repositoryPermission.findByDescription("PATIENT")
			.orElseGet(() -> createPermission("PATIENT"));
		
		newUser.setPermissions(List.of(patientPermission));
		User savedUser = userRepository.save(newUser);
		
		Patient newPatient = patientMapper.toEntity(patientDTO);
		newPatient.setUser(savedUser);
		
		Patient savedPatient = patientRepository.save(newPatient);
		
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
			userDTO.getUserName(),
			userDTO.getPassword()
		);
		
		Authentication authentication = authenticationManager.authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		List<String> roles = authentication.getAuthorities().stream()
			.map(GrantedAuthority::getAuthority)
			.collect(Collectors.toList());
		
		TokenDTO tokenDto = tokenProvider.createAccessToken(
			authentication.getName(),
			roles
		);
		
		Map<String, Object> response = new HashMap<>();
		response.put("patient", patientMapper.toResponseDto(savedPatient));
		response.put("token", tokenDto);
		
		return response;
	}
	
	private Permission createPermission(String description) {
		Permission newPermission = new Permission();
		newPermission.setDescription(description);
		return repositoryPermission.save(newPermission);
	}
}