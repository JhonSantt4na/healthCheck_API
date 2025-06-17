package com.santt4na.health_check.service.impl;

import com.santt4na.health_check.dto.appointmentDTO.AppointmentRequestDTO;
import com.santt4na.health_check.dto.appointmentDTO.AppointmentResponseDTO;
import com.santt4na.health_check.service.AppointmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	@Override
	public AppointmentResponseDTO createAppointment(AppointmentRequestDTO dto) {
		return null;
	}
	
	@Override
	public AppointmentResponseDTO updateAppointment(Long id, AppointmentRequestDTO dto) {
		return null;
	}
	
	@Override
	public void cancelAppointment(Long id) {
	
	}
	
	@Override
	public AppointmentResponseDTO findByIdAppointment(Long id) {
		return null;
	}
	
	@Override
	public List<AppointmentResponseDTO> findAllAppointment() {
		return List.of();
	}
	
	@Override
	public List<AppointmentResponseDTO> findByDoctor(Long doctorId) {
		return List.of();
	}
	
	@Override
	public List<AppointmentResponseDTO> findByPatient(Long patientId) {
		return List.of();
	}
}
