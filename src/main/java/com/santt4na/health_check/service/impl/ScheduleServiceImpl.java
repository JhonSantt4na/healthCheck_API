package com.santt4na.health_check.service.impl;

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
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
	
	private final ScheduleRepository repository;
	private final DoctorRepository doctorRepository;
	private final ScheduleMapper mapper;

	@Transactional
	public ScheduleResponseDTO createSchedule(ScheduleRequestDTO dto) {
		Doctor doctor = doctorRepository.findById(dto.doctorId())
			.orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado"));
		
		Schedule schedule = new Schedule();
		schedule.setDoctor(doctor);
		schedule.setStartTime(dto.startTime());
		schedule.setEndTime(dto.endTime());
		schedule.setAvailable(dto.available());
		
		Schedule saved = repository.save(schedule);
		return ScheduleResponseDTO.fromEntity(saved);
	}
	
	@Override
	@Transactional
	public ScheduleResponseDTO updateSchedule(Long id, ScheduleUpdateDTO dto) {
		Schedule existing = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Schedule not found with ID: " + id));
		
		Hibernate.initialize(existing.getDoctor());
		mapper.updateEntityFromDto(dto, existing);
		Schedule updated = repository.save(existing);
		
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
		
		Schedule existing = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Schedule not found com ID: " + id));
		repository.delete(existing);
	}
	
	@Transactional
	public ScheduleResponseDTO findById(Long id) {
		Schedule schedule = repository.findByIdWithDoctor(id)
			.orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado com ID: " + id));
		
		return ScheduleResponseDTO.fromEntity(schedule);
	}
	
	@Override
	@Transactional
	public List<ScheduleResponseDTO> findAll() {
		List<Schedule> schedules = repository.findAllWithDoctor();
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
		return repository.findByDoctorIdAndAvailableTrue(doctorId)
			.stream()
			.map(mapper::responseDto)
			.collect(Collectors.toList());
	}
}