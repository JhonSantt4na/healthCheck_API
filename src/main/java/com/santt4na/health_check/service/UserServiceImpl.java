package com.santt4na.health_check.service;

import com.santt4na.health_check.dto.securityDTO.UserResponseDTO;
import com.santt4na.health_check.service.impl.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	@Override
	public UserResponseDTO findEmail(String email) {
		return null;
	}
	
	@Override
	public boolean emailExists(String email) {
		return false;
	}
	
	@Override
	public String authenticate(String email, String password) {
		return "";
	}
	
	@Override
	public void replacePassword(Long userId, String newPassword) {
	
	}
}
