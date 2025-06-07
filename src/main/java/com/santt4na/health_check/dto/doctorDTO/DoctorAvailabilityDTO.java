package com.santt4na.health_check.dto.doctorDTO;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record DoctorAvailabilityDTO(
	DayOfWeek dayOfWeek,
	LocalTime startTime,
	LocalTime endTime
) {
}
