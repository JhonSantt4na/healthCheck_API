package com.santt4na.health_check.dto.securityDTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public record UserLoginDTO(
	@Email @NotNull String email,
	@NotNull String password
) {
}
