package com.santt4na.health_check.service;

import com.santt4na.health_check.dto.scheduleDTO.ScheduleRequestDTO;
import com.santt4na.health_check.dto.scheduleDTO.ScheduleResponseDTO;
import com.santt4na.health_check.dto.scheduleDTO.ScheduleUpdateDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ScheduleService {
	
	ScheduleResponseDTO createSchedule(ScheduleRequestDTO dto);
	ScheduleResponseDTO updateSchedule(Long id, ScheduleUpdateDTO dto);
	void deleteSchedule(Long id);
	ScheduleResponseDTO findById(Long id);
	List<ScheduleResponseDTO> findAll();
	List<ScheduleResponseDTO> findByDoctorId(Long doctorId);
}