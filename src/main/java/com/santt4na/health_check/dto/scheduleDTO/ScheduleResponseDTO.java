package com.santt4na.health_check.dto.scheduleDTO;

import com.santt4na.health_check.entity.Schedule;
import java.time.LocalDateTime;

public record ScheduleResponseDTO(
	
	Long id,
	Long doctorId,
	String doctorName,
	LocalDateTime startTime,
	LocalDateTime endTime,
	Boolean available
) {
	public static ScheduleResponseDTO fromEntity(Schedule schedule) {
		if (schedule == null) return null;
		
		return new ScheduleResponseDTO(
			schedule.getId(),
			schedule.getDoctor() != null ? schedule.getDoctor().getId() : null,
			schedule.getDoctor() != null ? schedule.getDoctor().getFullName() : null,
			schedule.getStartTime(),
			schedule.getEndTime(),
			schedule.getAvailable()
		);
	}
}