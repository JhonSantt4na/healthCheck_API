package com.santt4na.health_check.mapper;

import com.santt4na.health_check.dto.scheduleDTO.ScheduleRequestDTO;
import com.santt4na.health_check.dto.scheduleDTO.ScheduleResponseDTO;
import com.santt4na.health_check.dto.scheduleDTO.ScheduleUpdateDTO;
import com.santt4na.health_check.entity.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ScheduleMapper {
	
	@Mapping(target = "doctor", ignore = true)
	@Mapping(target = "id", ignore = true)
	Schedule toEntity(ScheduleRequestDTO dto);
	
	@Mapping(target = "doctorId", source = "doctor.id")
	@Mapping(target = "doctorName", source = "doctor.fullName")
	ScheduleResponseDTO responseDto(Schedule schedule);
	
	@Mapping(target = "doctor", ignore = true)
	@Mapping(target = "id", ignore = true)
	void updateEntityFromDto(ScheduleUpdateDTO dto, @MappingTarget Schedule entity);
}