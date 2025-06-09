package com.santt4na.health_check.service.impl;

import com.santt4na.health_check.dto.appointmentDTO.AppointmentRequestDTO;
import com.santt4na.health_check.dto.appointmentDTO.AppointmentResponseDTO;

import java.util.List;

public interface AppointmentService {
	
	AppointmentResponseDTO createAppointment(AppointmentRequestDTO dto);
	
	AppointmentResponseDTO updateAppointment(AppointmentRequestDTO dto);
	
	void cancelAppointment(Long id);
	
	AppointmentResponseDTO findByIdAppointment(Long id);
	
	List<AppointmentResponseDTO> findAllAppointment();
	
	List<AppointmentResponseDTO> findByDoctor(Long doctorId);
	
	List<AppointmentResponseDTO> findByPatient(Long patientId);
}
