package com.santt4na.health_check.dto.securityDTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record ChangeCredentialsDTO(
	@NotNull String oldPassword,
	@NotNull @Size(min = 8) String newPassword,
	@Email String newEmail
) {
}
