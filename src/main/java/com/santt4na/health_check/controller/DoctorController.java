package com.santt4na.health_check.controller;

import com.santt4na.health_check.controller.docs.DoctorControllerDocs;
import com.santt4na.health_check.dto.doctorDTO.DoctorCreateDTO;
import com.santt4na.health_check.dto.doctorDTO.DoctorResponseDTO;
import com.santt4na.health_check.dto.doctorDTO.DoctorUpdateDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor/v1")
public class DoctorController implements DoctorControllerDocs{
	
	@GetMapping(
		produces = {MediaType.APPLICATION_JSON_VALUE}
	)
	@Override
	public ResponseEntity<List<DoctorResponseDTO>> findAll() {
		return null;
	}
	
	@GetMapping(value = "/{id}",
		produces = {MediaType.APPLICATION_JSON_VALUE}
	)
	@Override
	public ResponseEntity<DoctorResponseDTO> findById(Long id) {
		return null;
	}
	
	@PostMapping(
		produces = {MediaType.APPLICATION_JSON_VALUE},
		consumes = {MediaType.APPLICATION_JSON_VALUE}
	)
	@Override
	public ResponseEntity<DoctorResponseDTO> create(DoctorCreateDTO person) {
		return null;
	}
	
	@PutMapping(
		produces = {MediaType.APPLICATION_JSON_VALUE},
		consumes = {MediaType.APPLICATION_JSON_VALUE}
	)
	@Override
	public ResponseEntity<DoctorResponseDTO> update(DoctorUpdateDTO person) {
		return null;
	}
	
	@DeleteMapping(value = "/{id}")
	@Override
	public ResponseEntity<Void> delete(Long id) {
		return null;
	}
}
