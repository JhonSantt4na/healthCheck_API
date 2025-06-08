package com.santt4na.health_check.mapper;

import com.santt4na.health_check.dto.appointmentDTO.*;
import com.santt4na.health_check.entity.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
	
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "status", ignore = true)
	@Mapping(target = "patient", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "doctor", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	Appointment requestToEntity(AppointmentRequestDTO appointmentRequestDTO);
	
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "reason", ignore = true)
	@Mapping(target = "patient", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "duration", ignore = true)
	@Mapping(target = "doctor", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "appointmentDate", ignore = true)
	Appointment approvalToEntity(AppointmentApprovalDTO appointmentApprovalDTO);
	
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "status", ignore = true)
	@Mapping(target = "reason", ignore = true)
	@Mapping(target = "patient", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "duration", ignore = true)
	@Mapping(target = "doctor", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "appointmentDate", ignore = true)
	Appointment cancelToEntity(AppointmentCancelDTO appointmentCancelDTO);
	
	AppointmentRequestDTO requestToDto(Appointment appointment);
	AppointmentApprovalDTO approvalToDto(Appointment appointment);
	AppointmentCancelDTO cancelToDto(Appointment appointment);
	AppointmentReportDTO reportToDto(Appointment appointment);
	AppointmentResponseDTO responseToDto(Appointment appointment);
}