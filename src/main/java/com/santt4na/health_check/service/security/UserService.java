package com.santt4na.health_check.service.security;

import com.santt4na.health_check.Startup;
import com.santt4na.health_check.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
	
	private static final Logger logger = LoggerFactory.getLogger(Startup.class);
	private static final Logger auditLogger = LoggerFactory.getLogger("audit");
	
	@Autowired
	UserRepository repository;
	
	public UserService(UserRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		logger.info("User lookup requested for username [{}]", username);
		var user = repository.findByUsername(username);
		if (user != null) {
			logger.info("User [{}] found in the database", username);
			return user;
		}else {
			logger.warn("User [{}] not found", username);
			throw new UsernameNotFoundException("Username "+ username +" not found!");
		}
	}
}