package com.santt4na.health_check.service;

import com.santt4na.health_check.dto.doctorDTO.DoctorResponseDTO;
import com.santt4na.health_check.dto.doctorDTO.DoctorUpdateDTO;
import java.util.List;

public interface DoctorService {
	
	DoctorResponseDTO updateDoctor(Long id, DoctorUpdateDTO dto);
	DoctorResponseDTO getDoctorById(Long id);
	List<DoctorResponseDTO> listAllDoctors();
	void deleteDoctor(Long id);
}