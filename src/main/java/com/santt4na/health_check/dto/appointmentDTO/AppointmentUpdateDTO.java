package com.santt4na.health_check.dto.appointmentDTO;

import com.santt4na.health_check.enums.AppointmentStatus;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public record AppointmentUpdateDTO(
	
	@NotNull(message = "Appointment date is required")
	LocalDateTime appointmentDate,
	
	@Min(value = 15, message = "Minimum duration is 15 minutes")
	@NotNull(message = "Duration is required")
	Integer duration,
	
	@Size(max = 255, message = "Reason must be 255 characters max")
	String reason,
	
	@NotNull Long patientId,
	
	@NotNull Long doctorId,
	
	@NotNull Long scheduleId,
	
	@NotNull(message = "Status is required")
	AppointmentStatus status
	
) { }