package com.santt4na.health_check.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.jdbc.core.JdbcTemplate;

public class DatabaseHealthIndicator implements HealthIndicator {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Health health() {
		try {
			jdbcTemplate.queryForObject("SELECT 1", Integer.class);
			return Health.up().withDetail("database", "Connection OK").build();
		} catch (Exception e) {
			return Health.down().withDetail("database", "Connection FAILED")
				.withException(e)
				.build();
		}
	}
}
