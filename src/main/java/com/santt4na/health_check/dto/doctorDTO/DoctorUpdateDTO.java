package com.santt4na.health_check.dto.doctorDTO;

import com.santt4na.health_check.enums.Gender;
import com.santt4na.health_check.enums.Specialty;
import com.santt4na.health_check.enums.UserState;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public record DoctorUpdateDTO(
	@Pattern(regexp = "\\+?\\d{2}\\s?\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}") String phone,
	Specialty specialty,
	@NotNull Gender gender,
	UserState status,
	boolean accountActive
) {
}
