package com.santt4na.health_check.service.impl;

import com.santt4na.health_check.dto.patientDTO.PatientRequestDTO;
import com.santt4na.health_check.dto.patientDTO.PatientResponseDTO;
import com.santt4na.health_check.dto.patientDTO.PatientUpdateDTO;
import com.santt4na.health_check.entity.Appointment;

import java.util.List;

public interface PatientService {
	
	PatientResponseDTO createPatient(PatientRequestDTO dto);
	
	PatientResponseDTO updatePatient(Long id, PatientUpdateDTO dto);
	
	PatientResponseDTO getPatientById(Long id);
	
	List<PatientResponseDTO> listAllPatient();
	
	void deletePatient(Long id);
	
	List<Appointment> getPatientAppointments(Long patientId);
}
