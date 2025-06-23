package com.santt4na.health_check.dto.appointmentDTO;

import com.santt4na.health_check.enums.AppointmentStatus;
import com.santt4na.health_check.enums.CancelledBy;
import java.time.LocalDateTime;

public record AppointmentResponseDTO(
	Long id,
	LocalDateTime appointmentDate,
	Integer duration,
	String reason,
	Long patientId,
	Long doctorId,
	Long scheduleId,
	AppointmentStatus status,
	LocalDateTime createdAt,
	LocalDateTime confirmedAt,
	CancelledBy cancelledBy
) {}