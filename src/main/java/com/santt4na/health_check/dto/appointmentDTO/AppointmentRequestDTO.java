package com.santt4na.health_check.dto.appointmentDTO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public record AppointmentRequestDTO(
	@NotNull(message = "Appointment date is required")
	LocalDateTime appointmentDate,
	
	@Min(value = 15, message = "Minimum duration is 15 minutes")
	@NotNull(message = "Duration is required")
	Integer duration,
	
	@Size(max = 255, message = "Reason must be 255 characters max")
	String reason,
	
	@NotNull(message = "Patient ID is required")
	Long patientId,
	
	@NotNull(message = "Doctor ID is required")
	Long doctorId,
	
	@NotNull(message = "Schedule ID is required")
	Long scheduleId
) {}