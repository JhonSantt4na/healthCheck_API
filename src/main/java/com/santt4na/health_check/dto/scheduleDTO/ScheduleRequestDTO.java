package com.santt4na.health_check.dto.scheduleDTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

public record ScheduleRequestDTO(
	
	@NotNull(message = "Doctor ID is required")
	Long doctorId,
	
	@NotNull(message = "Start time is required")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	LocalDateTime startTime,
	
	@NotNull(message = "End time is required")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	LocalDateTime endTime,
	
	Boolean available
) {
	public ScheduleRequestDTO {
		Objects.requireNonNull(doctorId, "Doctor ID cannot be null");
		Objects.requireNonNull(startTime, "Start time cannot be null");
		Objects.requireNonNull(endTime, "End time cannot be null");
		
		if (endTime.isBefore(startTime)) {
			throw new IllegalArgumentException("End time must be after start time");
		}
		
		available = Objects.requireNonNullElse(available, true);
	}
}