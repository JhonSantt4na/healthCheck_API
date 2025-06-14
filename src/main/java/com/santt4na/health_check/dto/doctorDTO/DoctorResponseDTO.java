package com.santt4na.health_check.dto.doctorDTO;

import com.santt4na.health_check.enums.Gender;
import com.santt4na.health_check.enums.Specialty;

import java.time.LocalDateTime;

public record DoctorResponseDTO(
	
	Long id,
	String fullName,
	Gender gender,
	String phone,
	Specialty specialty,
	String medicalLicense,
	LocalDateTime createdAt,
	LocalDateTime updatedAt
	
) {
}
