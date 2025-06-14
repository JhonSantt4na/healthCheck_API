package com.santt4na.health_check.mapper;

import com.santt4na.health_check.dto.doctorDTO.DoctorRequestDTO;
import com.santt4na.health_check.dto.doctorDTO.DoctorResponseDTO;
import com.santt4na.health_check.dto.doctorDTO.DoctorUpdateDTO;
import com.santt4na.health_check.entity.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
	componentModel = "spring",
	nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface DoctorMapper {
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "appointments", ignore = true)
	Doctor toEntity(DoctorRequestDTO dto);
	
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "appointments", ignore = true)
	void updateEntityFromDto(DoctorUpdateDTO dto, @MappingTarget Doctor entity);
	
	DoctorRequestDTO toRequestDto(Doctor entity);
	DoctorUpdateDTO toUpdateDto(Doctor entity);
	DoctorResponseDTO toResponseDto(Doctor entity);
}