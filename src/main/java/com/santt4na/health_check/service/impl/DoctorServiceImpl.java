package com.santt4na.health_check.service.impl;

import com.santt4na.health_check.dto.doctorDTO.DoctorRequestDTO;
import com.santt4na.health_check.dto.doctorDTO.DoctorResponseDTO;
import com.santt4na.health_check.dto.doctorDTO.DoctorUpdateDTO;
import com.santt4na.health_check.entity.Appointment;
import com.santt4na.health_check.mapper.DoctorMapper;
import com.santt4na.health_check.repository.DoctorRepository;
import com.santt4na.health_check.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
	
	@Autowired
	private DoctorMapper mapper;
	
	@Autowired
	private DoctorRepository repository;
	
	// paused 0/4
	@Override
	public DoctorResponseDTO createDoctor(DoctorRequestDTO dto) {
		//Doctor newDoctor = mapper.toEntity(dto);
		//newDoctor
		
		// 1/4 Validar Email duplicado
		// 2/4 Lanca uma Exception personalizada
		// 3/4 Criptografar Senha
		// 4/4 Adicionar os Logs
		
		//Doctor savedDoctor = repository.save(newDoctor);
		//return mapper.responseToDto(savedDoctor);
		return null;
	}
	
	@Override
	public DoctorResponseDTO updateDoctor(Long id, DoctorUpdateDTO dto) {
		return null;
	}
	
	@Override
	public DoctorResponseDTO getDoctorById(Long id) {
		return null;
	}
	
	@Override
	public List<DoctorResponseDTO> listAllDoctors() {
		return List.of();
	}
	
	@Override
	public void deleteDoctor(Long id) {
	
	}
	
	@Override
	public List<Appointment> getDoctorAppointments(Long doctorId) {
		return List.of();
	}
}
