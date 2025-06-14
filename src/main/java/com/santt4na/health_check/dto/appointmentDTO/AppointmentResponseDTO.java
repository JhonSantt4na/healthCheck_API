package com.santt4na.health_check.dto.appointmentDTO;

import com.santt4na.health_check.entity.Doctor;
import com.santt4na.health_check.entity.Patient;
import com.santt4na.health_check.enums.AppointmentStatus;
import java.time.LocalDateTime;

public record AppointmentResponseDTO(
	
	Long id,
	LocalDateTime appointmentDate,
	Integer duration,
	String reason,
	Patient patient,
	Doctor doctor,
	AppointmentStatus status,
	LocalDateTime createdAt,
	LocalDateTime updatedAt

) {
}
