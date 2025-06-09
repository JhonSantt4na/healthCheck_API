package com.santt4na.health_check.mapper;

import com.santt4na.health_check.dto.doctorDTO.DoctorAvailabilityDTO;
import com.santt4na.health_check.dto.doctorDTO.DoctorCreateDTO;
import com.santt4na.health_check.dto.doctorDTO.DoctorResponseDTO;
import com.santt4na.health_check.dto.doctorDTO.DoctorUpdateDTO;
import com.santt4na.health_check.entity.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
	
	@Mapping(target = "email", source = "email")
	@Mapping(target = "password", source = "password")
	@Mapping(target = "name", source = "name")
	@Mapping(target = "phone", source = "phone")
	@Mapping(target = "specialty", source = "specialty")
	@Mapping(target = "gender", source = "gender")
	@Mapping(target = "medicalLicense", source = "medicalLicense")
	@Mapping(target = "appointments", ignore = true)
	Doctor createToEntity(DoctorCreateDTO doctorCreateDTO);
	
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "status", ignore = true)
	@Mapping(target = "specialty", ignore = true)
	@Mapping(target = "role", ignore = true)
	@Mapping(target = "phone", ignore = true)
	@Mapping(target = "password", ignore = true)
	@Mapping(target = "name", ignore = true)
	@Mapping(target = "medicalLicense", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "gender", ignore = true)
	@Mapping(target = "email", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "appointments", ignore = true)
	@Mapping(target = "accountActive", ignore = true)
	Doctor availabilityToEntity(DoctorAvailabilityDTO doctorAvailabilityDTO);
	
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "role", ignore = true)
	@Mapping(target = "password", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "appointments", ignore = true)
	@Mapping(target = "accountActive", ignore = true)
	Doctor responseToEntity(DoctorResponseDTO doctorResponseDTO);
	
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "role", ignore = true)
	@Mapping(target = "password", ignore = true)
	@Mapping(target = "name", ignore = true)
	@Mapping(target = "medicalLicense", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "email", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "appointments", ignore = true)
	Doctor updatedToEntity(DoctorUpdateDTO doctorUpdateDTO);
	
	DoctorCreateDTO createToDto(Doctor doctor);
	DoctorCreateDTO availabilityToDto(Doctor doctor);
	DoctorResponseDTO responseToDto(Doctor doctor);
	DoctorUpdateDTO updateToDto (Doctor doctor);
}