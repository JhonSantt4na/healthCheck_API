package com.santt4na.health_check.service.impl;

import com.santt4na.health_check.Startup;
import com.santt4na.health_check.dto.doctorDTO.DoctorResponseDTO;
import com.santt4na.health_check.dto.doctorDTO.DoctorUpdateDTO;
import com.santt4na.health_check.entity.Doctor;
import com.santt4na.health_check.exception.ResourceNotFoundException;
import com.santt4na.health_check.mapper.DoctorMapper;
import com.santt4na.health_check.repository.DoctorRepository;
import com.santt4na.health_check.service.DoctorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
	
	private static final Logger logger = LoggerFactory.getLogger(Startup.class);
	private static final Logger auditLogger = LoggerFactory.getLogger("audit");
	
	private final DoctorMapper mapper;
	private final DoctorRepository repository;
	
	@Transactional
	@Override
	public DoctorResponseDTO updateDoctor(Long id, DoctorUpdateDTO dto) {
		logger.info("Starting Updated one new User Doctor By Id");
		auditLogger.info("Creating one new User!");
		
		Doctor searchDoctor = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + id));
		
		mapper.updateEntityFromDto(dto, searchDoctor);
		Doctor updated = repository.save(searchDoctor);
		
		auditLogger.info("Audit: Updated doctor ID {}", id);
		return mapper.toResponseDto(updated);
	}
	
	@Transactional
	@Override
	public DoctorResponseDTO getDoctorById(Long id) {
		
		logger.info("Fetching doctor by ID: {}", id);
		Doctor doctor = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + id));
		return mapper.toResponseDto(doctor);
	}
	
	@Transactional
	@Override
	public List<DoctorResponseDTO> listAllDoctors() {
		logger.info("List All Doctors");
		auditLogger.info("Audit: List All Doctors");
		
		List<Doctor> doctors = repository.findAll();
		return doctors.stream()
			.map(mapper::toResponseDto)
			.collect(Collectors.toList());
	}
	
	@Transactional
	@Override
	public void deleteDoctor(Long id) {
		logger.info("Deleting doctor with ID: {}", id);
		Doctor doctor = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
		
		repository.delete(doctor);
		auditLogger.info("Audit: Deleted doctor with ID {}", id);
	}
	
}