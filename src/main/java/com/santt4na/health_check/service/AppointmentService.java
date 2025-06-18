package com.santt4na.health_check.service;

import com.santt4na.health_check.dto.appointmentDTO.AppointmentRequestDTO;
import com.santt4na.health_check.dto.appointmentDTO.AppointmentResponseDTO;

import java.util.List;

public interface AppointmentService {
	
	AppointmentResponseDTO createAppointment(AppointmentRequestDTO dto);
	
	AppointmentResponseDTO updateAppointment(Long id, AppointmentRequestDTO dto);
	
	void cancelAppointment(Long id, String description);
	
	AppointmentResponseDTO findByIdAppointment(Long id);
	
	List<AppointmentResponseDTO> findAllAppointment();
	
	List<AppointmentResponseDTO> findByDoctor(Long doctorId);
	
	List<AppointmentResponseDTO> getDoctorAppointments(Long doctorId);
	
	List<AppointmentResponseDTO> getPatientAppointments(Long patientId);
}
