package com.santt4na.health_check.service;

import com.santt4na.health_check.dto.patientDTO.PatientResponseDTO;
import com.santt4na.health_check.dto.patientDTO.PatientUpdateDTO;

import java.util.List;

public interface PatientService {
	
	PatientResponseDTO updatePatient(Long id, PatientUpdateDTO dto);
	
	PatientResponseDTO getPatientById(Long id);
	
	List<PatientResponseDTO> listAllPatient();
	
	void deletePatient(Long id);
}
