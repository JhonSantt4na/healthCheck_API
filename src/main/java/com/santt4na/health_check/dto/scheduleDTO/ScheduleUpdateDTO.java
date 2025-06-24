package com.santt4na.health_check.dto.scheduleDTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record ScheduleUpdateDTO(
	
	@NotNull(message = "Start time is required")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	LocalDateTime startTime,
	
	@NotNull(message = "End time is required")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	LocalDateTime endTime,
	
	Boolean available
) {}