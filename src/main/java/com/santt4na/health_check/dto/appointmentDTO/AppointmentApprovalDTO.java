package com.santt4na.health_check.dto.appointmentDTO;

import com.santt4na.health_check.enums.AppointmentStatus;

public record AppointmentApprovalDTO(
	Long appointmentId,
	AppointmentStatus status,
	String adminNotes
) {
}
