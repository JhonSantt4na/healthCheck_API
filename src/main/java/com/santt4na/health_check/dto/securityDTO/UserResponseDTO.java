package com.santt4na.health_check.dto.securityDTO;

import com.santt4na.health_check.enums.Roles;
import com.santt4na.health_check.enums.UserState;

import java.time.LocalDateTime;

public record UserResponseDTO(
	Long id,
	String email,
	Roles role,
	UserState status,
	LocalDateTime createdAt
) {
}
