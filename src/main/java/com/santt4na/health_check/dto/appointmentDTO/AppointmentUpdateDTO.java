package com.santt4na.health_check.dto.appointmentDTO;

import com.santt4na.health_check.entity.Doctor;
import com.santt4na.health_check.entity.Patient;
import com.santt4na.health_check.enums.AppointmentStatus;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public record AppointmentUpdateDTO(
	
	@NotNull LocalDateTime appointmentDate,
	Integer duration,
	@Size(max = 255) String reason,
	@NotNull Patient patient,
	@NotNull Doctor doctor,
	@NotNull AppointmentStatus status
	
) {
}
