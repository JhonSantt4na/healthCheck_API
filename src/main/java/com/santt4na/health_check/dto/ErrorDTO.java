package com.santt4na.health_check.dto;

import java.time.LocalDateTime;

public record ErrorDTO(
	String code,
	String message,
	LocalDateTime timestamp
) {}
