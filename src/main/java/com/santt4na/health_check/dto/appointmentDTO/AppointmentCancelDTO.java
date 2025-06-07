package com.santt4na.health_check.dto.appointmentDTO;

public record AppointmentCancelDTO(
	Long appointmentId,
	String cancellationReason
) {
}
