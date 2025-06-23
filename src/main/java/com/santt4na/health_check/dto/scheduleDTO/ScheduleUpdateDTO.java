package com.santt4na.health_check.dto.scheduleDTO;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record ScheduleUpdateDTO(
	
	@NotNull LocalDateTime startTime,
	@NotNull LocalDateTime endTime,
	Boolean available
) {}