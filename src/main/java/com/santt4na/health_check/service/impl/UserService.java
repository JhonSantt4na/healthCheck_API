package com.santt4na.health_check.service.impl;

import com.santt4na.health_check.dto.securityDTO.UserResponseDTO;

public interface UserService {
	
	UserResponseDTO findEmail(String email);
	
	boolean emailExists (String email);
	
	String authenticate(String email, String password);
	
	void replacePassword(Long userId, String newPassword);
}
