package com.santt4na.health_check.service.impl;

import com.santt4na.health_check.Startup;
import com.santt4na.health_check.dto.scheduleDTO.ScheduleResponseDTO;
import com.santt4na.health_check.dto.appointmentDTO.AppointmentRequestDTO;
import com.santt4na.health_check.dto.appointmentDTO.AppointmentResponseDTO;
import com.santt4na.health_check.dto.appointmentDTO.AppointmentUpdateDTO;
import com.santt4na.health_check.entity.Appointment;
import com.santt4na.health_check.entity.Doctor;
import com.santt4na.health_check.entity.Patient;
import com.santt4na.health_check.entity.Schedule;
import com.santt4na.health_check.enums.AppointmentStatus;
import com.santt4na.health_check.enums.CancelledBy;
import com.santt4na.health_check.exception.BusinessException;
import com.santt4na.health_check.exception.ResourceNotFoundException;
import com.santt4na.health_check.mapper.AppointmentMapper;
import com.santt4na.health_check.mapper.ScheduleMapper;
import com.santt4na.health_check.repository.AppointmentRepository;
import com.santt4na.health_check.repository.DoctorRepository;
import com.santt4na.health_check.repository.PatientRepository;
import com.santt4na.health_check.repository.ScheduleRepository;
import com.santt4na.health_check.service.AppointmentService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	
	private static final Logger logger = LoggerFactory.getLogger(Startup.class);
	private static final Logger auditLogger = LoggerFactory.getLogger("audit");
	
	@Autowired
	private AppointmentMapper appointmentMapper;
	
	@Autowired
	private ScheduleMapper scheduleMapper;
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Override
	@Transactional
	public AppointmentResponseDTO createAppointment(AppointmentRequestDTO dto) {
		
		if (dto.patientId() == null) {
			throw new IllegalArgumentException("Patient ID is required");
		}
		if (dto.doctorId() == null) {
			throw new IllegalArgumentException("Doctor ID is required");
		}
		if (dto.scheduleId() == null) {
			throw new IllegalArgumentException("Schedule ID is required");
		}
		
		Doctor doctor = doctorRepository.findById(dto.doctorId())
			.orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
		
		Patient patient = patientRepository.findById(dto.patientId())
			.orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
		
		Schedule schedule = scheduleRepository.findById(dto.scheduleId())
			.orElseThrow(() -> new ResourceNotFoundException("Schedule not found"));
		
		if (!dto.appointmentDate().isEqual(schedule.getStartTime())) {
			throw new BusinessException("A data do agendamento deve coincidir com o início do horário marcado");
		}
		
		if (doctor == null || patient == null) {
			throw new ResourceNotFoundException("Required entities not found");
		}
		
		schedule.setAvailable(false);
		scheduleRepository.save(schedule);
		
		Appointment appointment = appointmentMapper.requestToEntity(dto);
		appointment.setDoctor(doctor);
		appointment.setPatient(patient);
		appointment.setSchedule(schedule);
		appointment.setStatus(AppointmentStatus.PENDING);
		appointment.setDuration(dto.duration());
		
		Appointment saved = appointmentRepository.save(appointment);
		return appointmentMapper.responseToDto(saved);
	}
	
	@Override
	@Transactional
	public AppointmentResponseDTO updateAppointment(Long id, AppointmentUpdateDTO dto) {
		Appointment existing = appointmentRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
		
		if (dto.appointmentDate() != null) {
			existing.setAppointmentDate(dto.appointmentDate());
		}
		if (dto.duration() != null) {
			existing.setDuration(dto.duration());
		}
		if (dto.reason() != null) {
			existing.setReason(dto.reason());
		}
		if (dto.status() != null) {
			existing.setStatus(dto.status());
		}
		
		Appointment updated = appointmentRepository.save(existing);
		return appointmentMapper.responseToDto(updated);
	}
	
	@Override
	@Transactional
	public void cancelAppointment(Long id, String reason) {
		Appointment appointment = appointmentRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
		
		appointment.setStatus(AppointmentStatus.CANCELED);
		appointment.setReason(reason);
		
		Schedule schedule = appointment.getSchedule();
		schedule.setAvailable(true);
		scheduleRepository.save(schedule);
		
		appointmentRepository.save(appointment);
	}
	
	@Override
	@Transactional
	public AppointmentResponseDTO findByIdAppointment(Long id) {
		Appointment appointment = appointmentRepository.findByIdWithDetails(id)
			.orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
		return appointmentMapper.responseToDto(appointment);
	}
	
	@Override
	@Transactional
	public List<AppointmentResponseDTO> findAllAppointment() {
		return appointmentRepository.findAllWithDetails().stream()
			.map(appointmentMapper::responseToDto)
			.toList();
	}
	
	@Override
	@Transactional
	public List<AppointmentResponseDTO> getDoctorAppointments(Long doctorId) {
		return appointmentRepository.findByDoctor_Id(doctorId).stream()
			.map(appointmentMapper::responseToDto)
			.collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public List<AppointmentResponseDTO> getPatientAppointments(Long patientId) {
		return appointmentRepository.findByPatient_Id(patientId).stream()
			.map(appointmentMapper::responseToDto)
			.collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public AppointmentResponseDTO confirmAppointment(Long id) {
		
		Appointment appointment = appointmentRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Appointment not found with ID: " + id));
		
		appointment.setStatus(AppointmentStatus.SCHEDULED);
		appointment.setConfirmedAt(LocalDateTime.now());
		Appointment confirmed = appointmentRepository.save(appointment);
		
		auditLogger.info("Appointment confirmed with ID {}", id);
		return appointmentMapper.responseToDto(confirmed);
	}
	
	@Override
	@Transactional
	public AppointmentResponseDTO cancelAppointmentByDoctor(Long id, String reason) {
		Appointment appointment = appointmentRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Appointment not found with ID: " + id));
		appointment.setStatus(AppointmentStatus.CANCELED);
		appointment.setCancelledBy(CancelledBy.DOCTOR);
		appointment.setReason(reason);
		
		Schedule schedule = appointment.getSchedule();
		schedule.setAvailable(true);
		scheduleRepository.save(schedule);
		
		Appointment canceled = appointmentRepository.save(appointment);
		auditLogger.info("Appointment canceled by doctor with ID {}", id);
		return appointmentMapper.responseToDto(canceled);
	}
	
	@Override
	@Transactional
	public AppointmentResponseDTO cancelAppointmentByPatient(Long id, String reason) {
		
		Appointment appointment = appointmentRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Appointment not found with ID: " + id));
		
		appointment.setStatus(AppointmentStatus.CANCELED);
		appointment.setCancelledBy(CancelledBy.PATIENT);
		appointment.setReason(reason);
		
		Schedule schedule = appointment.getSchedule();
		schedule.setAvailable(true);
		scheduleRepository.save(schedule);
		
		Appointment canceled = appointmentRepository.save(appointment);
		auditLogger.info("Appointment canceled by patient with ID {}", id);
		return appointmentMapper.responseToDto(canceled);
	}
	
	@Override
	@Transactional
	public List<ScheduleResponseDTO> getAvailableSchedules(Long doctorId) {
		return scheduleRepository.findByDoctorIdAndAvailableTrue(doctorId).stream()
			.map(scheduleMapper::responseDto)
			.collect(Collectors.toList());
	}
}