package com.santt4na.health_check.dto.securityDTO;

public record TokenResponseDTO(
	String token,
	String refreshToken,
	Long expiresIn,
	UserResponseDTO user
) {
}
