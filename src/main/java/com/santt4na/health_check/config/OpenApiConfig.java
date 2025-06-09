package com.santt4na.health_check.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
	
	@Bean
	OpenAPI customOpenAPI(){
		return new OpenAPI()
			.info(new Info()
				.title("HealthCheck API REST")
				.version("v1")
				.description("Focada no agendamento de consultas médicas, Com o objetivo de proporcionar uma experiência" +
					"real de desenvolvimento profissional, aplicando as melhores práticas de backend modernas.")
				.termsOfService("https://github.com/JhonSantt4na/healthCheck_API.git")
				.license(new License()
					.name("Apache 2.0")
					.url("https://github.com/JhonSantt4na/healthCheck_API.git")
				)
			);
	}
}
