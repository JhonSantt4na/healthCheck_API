package com.santt4na.health_check.mapper;

import com.santt4na.health_check.dto.appointmentDTO.*;
import com.santt4na.health_check.entity.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(source = "patient", target = "patient")
	@Mapping(source = "doctor", target = "doctor")
	Appointment requestToEntity(AppointmentRequestDTO dto);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(source = "patient", target = "patient")
	@Mapping(source = "doctor", target = "doctor")
	Appointment updateToEntity(AppointmentUpdateDTO dto);
	
	AppointmentResponseDTO responseToDto(Appointment appointment);
	AppointmentRequestDTO requestToDto(Appointment appointment);
	AppointmentUpdateDTO updatedToDto(Appointment appointment);
}

