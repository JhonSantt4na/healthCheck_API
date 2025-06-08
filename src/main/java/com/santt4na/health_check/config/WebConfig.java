package com.santt4na.health_check.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
	
	@Value("${cors.originPatterns}")
	private String corsOriginPatterns = "";
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		
		var allowedOrigins = corsOriginPatterns.split(",");
		
		registry.addMapping("/**")
			.allowedOrigins(allowedOrigins)
			.allowedMethods("*")
			.allowCredentials(true);
	}
}