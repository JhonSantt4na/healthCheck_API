package com.santt4na.health_check.dto.patientDTO;

import com.santt4na.health_check.enums.Gender;

import javax.validation.constraints.*;
import java.time.LocalDate;

public record PatientCreateDTO(
	@Email @NotNull String email,
	@Size(min = 8) @NotNull String password,
	@Size(min = 2, max = 100) @NotNull String name,
	@Past LocalDate dateOfBirth,
	@Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}") @NotNull String cpf,
	@Pattern(regexp = "\\+?\\d{2}\\s?\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}") @NotNull String phone,
	@Size(max = 50) String healthInsurance,
	@NotNull Gender gender
) {
}
