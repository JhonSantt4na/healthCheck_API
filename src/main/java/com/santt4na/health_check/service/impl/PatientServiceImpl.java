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
import org.springframework.beans.factory.annotation.Autowired;
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
		logger.info("Starting Updated one new User Doctor By Id");
		auditLogger.info("Find By Id User!");
		
		Patient searchPatient = repository.findById(id)
			.orElseThrow(()-> new ResourceNotFoundException("Patient not found with ID:" + id));
		
		mapper.updateEntityFromDto(dto, searchPatient);
		Patient updated = repository.save(searchPatient);
		
		auditLogger.info("Audit: Updated Patient ID {}", id);
		return mapper.toResponseDto(updated);
	}
	
	@Transactional
	@Override
	public PatientResponseDTO getPatientById(Long id) {
		logger.info("Fetching patient by Id: {}", id);
		Patient patient = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Patient not found with ID:" + id));
		return mapper.toResponseDto(patient);
	}
	
	@Transactional
	@Override
	public List<PatientResponseDTO> listAllPatient() {
		List<Patient> patients = repository.findAll();
		return patients.stream()
			.map(mapper::toResponseDto)
			.collect(Collectors.toList());
	}
	
	@Transactional
	@Override
	public void deletePatient(Long id) {
		logger.info("Deleting patient with ID: {}", id);
		Patient patient = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
		
		repository.delete(patient);
		auditLogger.info("Audit: Deleted Patient with ID {}", id);
	}
	
}
