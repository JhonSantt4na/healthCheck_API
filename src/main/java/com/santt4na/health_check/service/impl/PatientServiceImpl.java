package com.santt4na.health_check.service.impl;

import com.santt4na.health_check.Startup;
import com.santt4na.health_check.dto.patientDTO.PatientResponseDTO;
import com.santt4na.health_check.dto.patientDTO.PatientUpdateDTO;
import com.santt4na.health_check.entity.Patient;
import com.santt4na.health_check.exception.ResourceNotFoundException;
import com.santt4na.health_check.mapper.PatientMapper;
import com.santt4na.health_check.repository.PatientRepository;
import com.santt4na.health_check.service.PatientService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
	
	private static final Logger logger = LoggerFactory.getLogger(Startup.class);
	private static final Logger auditLogger = LoggerFactory.getLogger("audit");
	
	private final PatientMapper mapper;
	private final PatientRepository repository;
	
	@Transactional
	@Override
	public PatientResponseDTO updatePatient(Long id, PatientUpdateDTO dto) {
		
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		logger.info("Request to update patient [{}]", id);
		Patient searchPatient = repository.findById(id)
			.orElseThrow(()-> new ResourceNotFoundException("Patient not found with ID:" + id));
		
		mapper.updateEntityFromDto(dto, searchPatient);
		Patient updated = repository.save(searchPatient);
		
		logger.info("Patient [{}] successfully updated", id);
		auditLogger.info("Patient [{}] updated by user [{}]", id, currentUser);
		return mapper.toResponseDto(updated);
	}
	
	@Transactional
	@Override
	public PatientResponseDTO getPatientById(Long id) {
		
		logger.info("Request to retrieve patient by ID [{}]", id);
		Patient patient = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Patient not found with ID:" + id));
		return mapper.toResponseDto(patient);
	}
	
	@Transactional
	@Override
	public List<PatientResponseDTO> listAllPatient() {
		
		logger.info("Request to list all patients");
		List<Patient> patients = repository.findAll();
		return patients.stream()
			.map(mapper::toResponseDto)
			.collect(Collectors.toList());
	}
	
	@Transactional
	@Override
	public void deletePatient(Long id) {
		
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		logger.info("Request to delete patient [{}]", id);
		Patient patient = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
		
		repository.delete(patient);
		logger.info("Patient [{}] successfully deleted", id);
		auditLogger.info("Patient [{}] deleted by user [{}]", id, currentUser);
	}
}