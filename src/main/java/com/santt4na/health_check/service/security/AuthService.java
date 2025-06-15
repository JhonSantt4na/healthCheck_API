package com.santt4na.health_check.service.security;

import com.santt4na.health_check.dto.securityDTO.AccountCredentialsDTO;
import com.santt4na.health_check.dto.securityDTO.TokenDTO;
import com.santt4na.health_check.repository.UserRepository;
import com.santt4na.health_check.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private UserRepository repository;
	
	public ResponseEntity<TokenDTO> signIn(AccountCredentialsDTO credentials){
		authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				credentials.getUserName(),
				credentials.getPassword())
		);
		
		var user = repository.findByUsername(credentials.getUserName());
		if (user == null) {
			throw new UsernameNotFoundException("Username " + credentials.getUserName() + " not found!");
		}
		
		var token = tokenProvider.createAccessToken(
			credentials.getUserName(), user.getRoles()
		);
		
		return ResponseEntity.ok(token);
	}
	
}
