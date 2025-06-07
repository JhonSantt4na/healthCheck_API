package com.santt4na.health_check.dto.doctorDTO;

import com.santt4na.health_check.enums.Gender;
import com.santt4na.health_check.enums.Specialty;
import com.santt4na.health_check.enums.UserState;

public record DoctorResponseDTO(
	Long id,
	String name,
	String email,
	String phone,
	Specialty specialty,
	Gender gender,
	String medicalLicense,
	UserState status
) {
}
