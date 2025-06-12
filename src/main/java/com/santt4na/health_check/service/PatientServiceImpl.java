package com.santt4na.health_check.service;

import com.santt4na.health_check.dto.patientDTO.PatientCreateDTO;
import com.santt4na.health_check.dto.patientDTO.PatientResponseDTO;
import com.santt4na.health_check.dto.patientDTO.PatientUpdateDTO;
import com.santt4na.health_check.entity.Appointment;
import com.santt4na.health_check.service.impl.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
	@Override
	public PatientResponseDTO createPatient(PatientCreateDTO dto) {
		return null;
	}
	
	@Override
	public PatientResponseDTO updatePatient(Long id, PatientUpdateDTO dto) {
		return null;
	}
	
	@Override
	public PatientResponseDTO getPatientById(Long id) {
		return null;
	}
	
	@Override
	public List<PatientResponseDTO> listAllPatient() {
		return List.of();
	}
	
	@Override
	public void deletePatient(Long id) {
	
	}
	
	@Override
	public List<Appointment> getPatientAppointments(Long patientId) {
		return List.of();
	}
}
