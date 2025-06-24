package com.santt4na.health_check.service.impl;

import com.santt4na.health_check.Startup;
import com.santt4na.health_check.dto.scheduleDTO.ScheduleRequestDTO;
import com.santt4na.health_check.dto.scheduleDTO.ScheduleResponseDTO;
import com.santt4na.health_check.dto.scheduleDTO.ScheduleUpdateDTO;
import com.santt4na.health_check.entity.Doctor;
import com.santt4na.health_check.entity.Schedule;
import com.santt4na.health_check.exception.ResourceNotFoundException;
import com.santt4na.health_check.mapper.ScheduleMapper;
import com.santt4na.health_check.repository.DoctorRepository;
import com.santt4na.health_check.repository.ScheduleRepository;
import com.santt4na.health_check.service.ScheduleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService  {
	
	private static final Logger logger = LoggerFactory.getLogger(Startup.class);
	private static final Logger auditLogger = LoggerFactory.getLogger("audit");
	
	private final ScheduleRepository repository;
	private final DoctorRepository doctorRepository;
	private final ScheduleMapper mapper;
	
	@Transactional
	public ScheduleResponseDTO createSchedule(ScheduleRequestDTO dto) {
		
		String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Doctor loggedDoctor = null;
		
		try {
			loggedDoctor = doctorRepository.findByUserUserName(currentUsername)
				.orElseThrow(() -> new AccessDeniedException("Authenticated user is not a valid doctor"));
		} catch (AccessDeniedException e) {
			throw new RuntimeException(e);
		}
		
		if (!loggedDoctor.getId().equals(dto.doctorId())) {
			try {
				throw new AccessDeniedException("Not allowed to create scheduling for another doctor");
			} catch (AccessDeniedException e) {
				throw new RuntimeException(e);
			}
		}
		
		logger.info("Request to create a new schedule");
		Doctor doctor = doctorRepository.findById(dto.doctorId())
			.orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado"));
		
		Schedule schedule = new Schedule();
		schedule.setDoctor(doctor);
		schedule.setStartTime(dto.startTime());
		schedule.setEndTime(dto.endTime());
		schedule.setAvailable(dto.available());
		
		Schedule saved = repository.save(schedule);
		
		logger.info("Schedule [{}] successfully created", schedule.getId());
		auditLogger.info("New schedule [{}] created by user [{}]", schedule.getId(), currentUsername);
		return ScheduleResponseDTO.fromEntity(saved);
	}
	
	@Override
	@Transactional
	public ScheduleResponseDTO updateSchedule(Long id, ScheduleUpdateDTO dto) {
		
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		logger.info("Request to update schedule [{}]", id);
		Schedule existing = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Schedule not found with ID: " + id));
		
		Hibernate.initialize(existing.getDoctor());
		mapper.updateEntityFromDto(dto, existing);
		Schedule updated = repository.save(existing);
		
		logger.info("Schedule [{}] successfully updated", id);
		auditLogger.info("Schedule [{}] updated by user [{}]", id, currentUser);
		
		return new ScheduleResponseDTO(
			updated.getId(),
			updated.getDoctor().getId(),
			updated.getDoctor().getFullName(),
			updated.getStartTime(),
			updated.getEndTime(),
			updated.getAvailable()
		);
	}
	
	@Override
	@Transactional
	public void deleteSchedule(Long id) {
		
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		logger.info("Request to delete schedule [{}]", id);
		Schedule existing = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Schedule not found com ID: " + id));
		
		logger.info("Schedule [{}] successfully deleted", id);
		auditLogger.info("Schedule [{}] deleted by user [{}]", id, currentUser);
		repository.delete(existing);
	}
	
	@Transactional
	public ScheduleResponseDTO findById(Long id) {
		Schedule schedule = repository.findByIdWithDoctor(id)
			.orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado com ID: " + id));
		logger.info("Request to retrieve schedule by ID [{}]", id);
		return ScheduleResponseDTO.fromEntity(schedule);
	}
	
	@Override
	@Transactional
	public List<ScheduleResponseDTO> findAll() {
		
		List<Schedule> schedules = repository.findAllWithDoctor();
		logger.info("Request to list all schedules");
		return schedules.stream()
			.map(schedule -> new ScheduleResponseDTO(
				schedule.getId(),
				schedule.getDoctor().getId(),
				schedule.getDoctor().getFullName(),
				schedule.getStartTime(),
				schedule.getEndTime(),
				schedule.getAvailable()
			))
			.toList();
	}
	
	@Override
	@Transactional
	public List<ScheduleResponseDTO> findByDoctorId(Long doctorId) {
		
		logger.info("Request to retrieve schedules for doctor [{}]", doctorId);
		return repository.findByDoctorIdAndAvailableTrue(doctorId)
			.stream()
			.map(mapper::responseDto)
			.collect(Collectors.toList());
	}
}