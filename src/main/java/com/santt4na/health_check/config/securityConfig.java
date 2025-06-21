package com.santt4na.health_check.config;

import com.santt4na.health_check.security.JwtTokenFilter;
import com.santt4na.health_check.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class securityConfig {
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		
		PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder(
			"", 8, 185000,
			Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
		
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put("pbkdf2", pbkdf2Encoder);
		DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
		
		passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);
		return passwordEncoder;
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		JwtTokenFilter filter = new JwtTokenFilter(tokenProvider);
		
		return http
			.csrf(AbstractHttpConfigurer::disable)
			.httpBasic(AbstractHttpConfigurer::disable)
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(auth -> auth
				.requestMatchers(
					"/auth/signin",
					"/auth/refresh/**",
					"/auth/createUser",
					"/swagger-ui/**",
					"/v3/api-docs/**"
				).permitAll()
				
				.requestMatchers("/api/doctor", "/api/doctor/{id}").hasAnyRole("PATIENT", "DOCTOR")
				
				.requestMatchers("/api/schedules/**").hasRole("DOCTOR")
				
				.requestMatchers("/api/appointments/patient/**").hasRole("PATIENT")
				.requestMatchers("/api/appointments").hasRole("PATIENT")
				
				.requestMatchers("/api/appointments/doctor/**").hasRole("DOCTOR")
				.requestMatchers("/api/appointments/*/confirm").hasRole("DOCTOR")
				.requestMatchers("/api/appointments/*/cancel/doctor").hasRole("DOCTOR")
				
				.requestMatchers("/api/appointments/*/cancel/patient").hasRole("PATIENT")
				
				.anyRequest().authenticated()
			)
			.formLogin(form -> form
				.loginPage("/auth/login")
				.defaultSuccessUrl("/", true)
				.permitAll()
			)
			.logout(logout -> logout.permitAll())
			.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
			.cors(Customizer.withDefaults())
			.build();
	}
}