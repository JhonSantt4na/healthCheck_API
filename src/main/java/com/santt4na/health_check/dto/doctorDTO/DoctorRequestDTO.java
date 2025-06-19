package com.santt4na.health_check.dto.doctorDTO;

import com.santt4na.health_check.enums.Gender;
import com.santt4na.health_check.enums.Specialty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public record DoctorRequestDTO(

	@Size(min = 2, max = 100) @NotNull String fullName,
	@Email @NotNull String email,
	@NotNull Gender gender,
	@Pattern(regexp = "\\+?\\d{2}\\s?\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}") @NotNull String phone,
	@NotNull Specialty specialty,
	@Pattern(regexp = "\\d{1,6}-[A-Z]{2}") @NotNull String medicalLicense

) {}