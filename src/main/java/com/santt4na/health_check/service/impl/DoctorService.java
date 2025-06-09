package com.santt4na.health_check.service.impl;

import com.santt4na.health_check.dto.doctorDTO.DoctorCreateDTO;
import com.santt4na.health_check.dto.doctorDTO.DoctorResponseDTO;
import com.santt4na.health_check.dto.doctorDTO.DoctorUpdateDTO;
import com.santt4na.health_check.entity.Appointment;

import java.util.List;

public interface DoctorService {
	
	DoctorResponseDTO createDoctor(DoctorCreateDTO dto);
	
	DoctorResponseDTO updateDoctor(Long id, DoctorUpdateDTO dto);
	
	DoctorResponseDTO getDoctorById(Long id);
	
	List<DoctorResponseDTO> listAllDoctors();
	
	void deleteDoctor(Long id);
	
	List<Appointment> getDoctorAppointments(Long doctorId);
}
