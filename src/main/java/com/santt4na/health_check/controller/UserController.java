package com.santt4na.health_check.controller;

import com.santt4na.health_check.controller.docs.UserControllerDocs;
import com.santt4na.health_check.dto.securityDTO.UserResponseDTO;
import com.santt4na.health_check.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/v1")
public class UserController implements UserControllerDocs {
	
	@Autowired
	private UserServiceImpl service;
	
	@GetMapping(
		produces = {MediaType.APPLICATION_JSON_VALUE}
	)
	@Override
	public ResponseEntity<UserResponseDTO> findEmail(String email) {
		return ResponseEntity.ok(service.findEmail(email));
	}
	
	@GetMapping(
		produces = {MediaType.APPLICATION_JSON_VALUE}
	)
	@Override
	public boolean emailExists(String email) {
		return service.emailExists(email);
	}
	
	@PostMapping(
		value = "/authenticate",
		consumes = {MediaType.APPLICATION_JSON_VALUE},
		produces = {MediaType.APPLICATION_JSON_VALUE}
	)
	@Override
	public ResponseEntity<String> authenticate(String email, String password) {
		return ResponseEntity.ok(service.authenticate(email, password));
	}
	
	@PostMapping(
		value = "/replacePassword",
		consumes = {MediaType.APPLICATION_JSON_VALUE},
		produces = {MediaType.APPLICATION_JSON_VALUE}
	)
	@Override
	public ResponseEntity<Void> replacePassword(Long userId, String newPassword) {
		service.replacePassword(userId, newPassword);
		return ResponseEntity.noContent().build();
	}
}
