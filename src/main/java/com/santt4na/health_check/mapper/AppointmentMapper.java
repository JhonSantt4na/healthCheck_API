package com.santt4na.health_check.mapper;

import com.santt4na.health_check.dto.appointmentDTO.AppointmentRequestDTO;
import com.santt4na.health_check.dto.appointmentDTO.AppointmentResponseDTO;
import com.santt4na.health_check.dto.appointmentDTO.AppointmentUpdateDTO;
import com.santt4na.health_check.entity.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AppointmentMapper {
	
	@Mapping(target = "confirmedAt", ignore = true)
	@Mapping(target = "cancelledBy", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "status", ignore = true) // Será definido no service
	@Mapping(source = "patientId", target = "patient.id")
	@Mapping(source = "doctorId", target = "doctor.id")
	@Mapping(source = "scheduleId", target = "schedule.id")
	Appointment requestToEntity(AppointmentRequestDTO dto);
	
	@Mapping(target = "confirmedAt", ignore = true)
	@Mapping(target = "cancelledBy", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(source = "patientId", target = "patient.id")
	@Mapping(source = "doctorId", target = "doctor.id")
	@Mapping(source = "scheduleId", target = "schedule.id")
	void updateToEntity(AppointmentUpdateDTO dto, @MappingTarget Appointment appointment);
	
	
	@Mapping(source = "patient.id", target = "patientId")
	@Mapping(source = "doctor.id", target = "doctorId")
	@Mapping(source = "schedule.id", target = "scheduleId")
	AppointmentResponseDTO responseToDto(Appointment appointment);
	
	List<AppointmentResponseDTO> responseListToDto(List<Appointment> appointments);

}