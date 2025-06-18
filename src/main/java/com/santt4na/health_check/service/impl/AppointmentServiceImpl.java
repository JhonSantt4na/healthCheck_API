package com.santt4na.health_check.service.impl;

import com.santt4na.health_check.Startup;
import com.santt4na.health_check.dto.appointmentDTO.AppointmentRequestDTO;
import com.santt4na.health_check.dto.appointmentDTO.AppointmentResponseDTO;
import com.santt4na.health_check.entity.Appointment;
import com.santt4na.health_check.entity.Doctor;
import com.santt4na.health_check.entity.Patient;
import com.santt4na.health_check.enums.AppointmentStatus;
import com.santt4na.health_check.exception.RequiredObjectIsNullException;
import com.santt4na.health_check.exception.ResourceNotFoundException;
import com.santt4na.health_check.mapper.AppointmentMapper;
import com.santt4na.health_check.mapper.DoctorMapper;
import com.santt4na.health_check.mapper.PatientMapper;
import com.santt4na.health_check.repository.AppointmentRepository;
import com.santt4na.health_check.repository.DoctorRepository;
import com.santt4na.health_check.repository.PatientRepository;
import com.santt4na.health_check.service.AppointmentService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	
	private static final Logger logger = LoggerFactory.getLogger(Startup.class);
	private static final Logger auditLogger = LoggerFactory.getLogger("audit");
	
	@Autowired
	private AppointmentMapper mapperAppointment;
	
	@Autowired
	private DoctorMapper mapperDoctor;
	
	@Autowired
	private PatientMapper mapperPatient;
	
	@Autowired
	private AppointmentRepository repositoryAppointment;
	
	@Autowired
	private DoctorRepository repositoryDoctor;
	
	@Autowired
	private PatientRepository repositoryPatient;
	
	@Override
	public AppointmentResponseDTO createAppointment(AppointmentRequestDTO dto) {
		return null;
	}
	
	@Override
	public AppointmentResponseDTO updateAppointment(Long id, AppointmentRequestDTO dto) {
		return null;
	}
	
	@Transactional
	@Override
	public void cancelAppointment(Long id, String description) {
		logger.info("Canceled Appointment by ID: {}", id);
		Appointment canceled = repositoryAppointment.findById(id)
			.orElseThrow(()-> new ResourceNotFoundException("Cancel Appointment!"));
		
		if (!StringUtils.hasText(description)) {
			throw new RequiredObjectIsNullException("Cancellation reason cannot be null or empty");
		}
		
		canceled.setReason(description);
		canceled.setStatus(AppointmentStatus.CANCELED);
	
		repositoryAppointment.save(canceled);
		auditLogger.info("Audit: Appointment Canceled with ID {}", id);
	}
	
	@Transactional
	@Override
	public AppointmentResponseDTO findByIdAppointment(Long id) {
		
		logger.info("Fetching Appointment by ID: {}", id);
		Appointment searchAppointment = repositoryAppointment.findById(id)
			.orElseThrow(()-> new ResourceNotFoundException("Appointment not found !"));
		
		return mapperAppointment.responseToDto(searchAppointment);
	}
	
	@Transactional
	@Override
	public List<AppointmentResponseDTO> findAllAppointment() {
		
		logger.info("List All Appointments");
		auditLogger.info("Audit: List All Appointments");
		
		List<Appointment> appointments = repositoryAppointment.findAll();
		return appointments.stream().map(mapperAppointment::responseToDto).collect(Collectors.toList());
	}
	
	@Override
	public List<AppointmentResponseDTO> findByDoctor(Long doctorId) {
		return List.of();
	}
	
	@Transactional
	@Override
	public List<AppointmentResponseDTO> getDoctorAppointments(Long doctorId) {
		
		logger.info("Find Appointments by doctor");
		auditLogger.info("Audit: Find Appointments by doctor");
		
		Doctor doctorAppointment = repositoryDoctor.findById(doctorId)
			.orElseThrow(()-> new ResourceNotFoundException("Doctor not found with ID: " + doctorId));
		
		List<Appointment> appointments = doctorAppointment.getAppointments();
		return mapperAppointment.responseListToDto(appointments);
	}
	
	@Transactional
	@Override
	public List<AppointmentResponseDTO> getPatientAppointments(Long patientId) {
		logger.info("Find Appointments by patient");
		auditLogger.info("Audit: Find Appointments by patient");
		
		Patient patientAppointment = repositoryPatient.findById(patientId)
			.orElseThrow(()-> new ResourceNotFoundException("Doctor not found with ID: " + patientId));
		
		List<Appointment> appointments = patientAppointment.getAppointments();
		return mapperAppointment.responseListToDto(appointments);
	}
}
