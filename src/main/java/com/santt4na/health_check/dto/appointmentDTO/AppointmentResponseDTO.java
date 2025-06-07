package com.santt4na.health_check.dto.appointmentDTO;

import com.santt4na.health_check.dto.doctorDTO.DoctorResponseDTO;
import com.santt4na.health_check.dto.patientDTO.PatientResponseDTO;
import com.santt4na.health_check.enums.AppointmentStatus;

import java.time.LocalDateTime;

public record AppointmentResponseDTO(
	Long id,
	LocalDateTime appointmentDate,
	Integer duration,
	String reason,
	AppointmentStatus status,
	LocalDateTime createdAt,
	PatientResponseDTO patient,
	DoctorResponseDTO doctor
) {
}
