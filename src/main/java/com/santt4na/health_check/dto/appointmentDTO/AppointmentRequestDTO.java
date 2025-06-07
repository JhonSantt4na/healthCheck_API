package com.santt4na.health_check.dto.appointmentDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public record AppointmentRequestDTO(
	@NotNull LocalDateTime appointmentDate,
	Integer duration,
	@Size(max = 255) String reason,
	@NotNull Long patientId,
	@NotNull Long doctorId
) {
}
