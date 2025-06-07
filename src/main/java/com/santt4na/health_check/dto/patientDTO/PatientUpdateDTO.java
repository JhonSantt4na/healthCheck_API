package com.santt4na.health_check.dto.patientDTO;

import com.santt4na.health_check.enums.Gender;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public record PatientUpdateDTO(
	@Pattern(regexp = "\\+?\\d{2}\\s?\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}") String phone,
	@Size(max = 50) String healthInsurance
) {
}

