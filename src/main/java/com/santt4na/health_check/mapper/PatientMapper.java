package com.santt4na.health_check.mapper;

import com.santt4na.health_check.dto.patientDTO.PatientCreateDTO;
import com.santt4na.health_check.dto.patientDTO.PatientResponseDTO;
import com.santt4na.health_check.dto.patientDTO.PatientUpdateDTO;
import com.santt4na.health_check.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PatientMapper {
	
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "status", ignore = true)
	@Mapping(target = "role", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "appointments", ignore = true)
	@Mapping(target = "accountActive", ignore = true)
	Patient createToEntity(PatientCreateDTO patientCreateDTO);
	
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "role", ignore = true)
	@Mapping(target = "password", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "email", ignore = true)
	@Mapping(target = "dateOfBirth", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "cpf", ignore = true)
	@Mapping(target = "appointments", ignore = true)
	Patient updeteToEntity(PatientUpdateDTO patientUpdateDTO);
	
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "role", ignore = true)
	@Mapping(target = "password", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "appointments", ignore = true)
	@Mapping(target = "accountActive", ignore = true)
	Patient responseToEntity(PatientResponseDTO patientResponseDTO);
	
	PatientCreateDTO createToDto (Patient patient);
	PatientUpdateDTO updateToDto (Patient patient);
	PatientResponseDTO responseToDto (Patient patient);
}