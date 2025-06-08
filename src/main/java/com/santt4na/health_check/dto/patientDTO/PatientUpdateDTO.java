package com.santt4na.health_check.dto.patientDTO;

import com.santt4na.health_check.enums.Gender;
import com.santt4na.health_check.enums.UserState;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public record PatientUpdateDTO(
	@Size(min = 2, max = 100) @NotNull String name,
	@Pattern(regexp = "\\+?\\d{2}\\s?\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}") String phone,
	@Size(max = 50) String healthInsurance,
	@NotNull Gender gender,
	UserState status,
	boolean accountActive
) {
}

