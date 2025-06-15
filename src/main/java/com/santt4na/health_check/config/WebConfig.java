package com.santt4na.health_check.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		
		String corsOriginPatterns = "";
		var allowedOrigins = corsOriginPatterns.split(",");
		
		registry.addMapping("/**")
			.allowedOrigins(allowedOrigins)
			.allowedMethods("*")
			.allowCredentials(true);
	}
}