package com.santt4na.health_check.mapper;

import com.santt4na.health_check.dto.patientDTO.PatientRequestDTO;
import com.santt4na.health_check.dto.patientDTO.PatientResponseDTO;
import com.santt4na.health_check.dto.patientDTO.PatientUpdateDTO;
import com.santt4na.health_check.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
	componentModel = "spring",
	nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface PatientMapper {
	
	@Mapping(target = "user", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "appointments", ignore = true)
	Patient toEntity(PatientRequestDTO dto);
	
	@Mapping(target = "user", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "appointments", ignore = true)
	Patient toEntity(PatientResponseDTO dto);
	
	@Mapping(target = "user", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "appointments", ignore = true)
	void updateEntityFromDto(PatientUpdateDTO dto, @MappingTarget Patient entity);
	
	PatientResponseDTO toResponseDto(Patient entity);
}