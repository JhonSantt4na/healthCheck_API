package com.santt4na.health_check.dto.appointmentDTO;

import com.santt4na.health_check.enums.AppointmentStatus;

import java.time.LocalDateTime;

public record AppointmentReportDTO(
	LocalDateTime startDate,
	LocalDateTime endDate,
	Long doctorId,
	AppointmentStatus status
) {
}
