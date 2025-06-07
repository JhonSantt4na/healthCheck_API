package com.santt4na.health_check.dto.securityDTO;

import com.santt4na.health_check.enums.Roles;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record UserRegisterDTO(
	@Email @NotNull String email,
	@Size(min = 8) @NotNull String password,
	@NotNull Roles role
) {
}
