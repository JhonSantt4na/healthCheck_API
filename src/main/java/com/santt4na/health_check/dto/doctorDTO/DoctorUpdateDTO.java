package com.santt4na.health_check.dto.doctorDTO;

import com.santt4na.health_check.enums.Specialty;

import javax.validation.constraints.Pattern;

public record DoctorUpdateDTO(
	@Pattern(regexp = "\\+?\\d{2}\\s?\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}") String phone,
	Specialty specialty,
	@Pattern(regexp = "\\d{1,6}-[A-Z]{2}")
	String medicalLicense
) {
}
