package com.santt4na.health_check.controller;

import com.santt4na.health_check.controller.docs.PatientControllerDocs;
import com.santt4na.health_check.dto.patientDTO.PatientCreateDTO;
import com.santt4na.health_check.dto.patientDTO.PatientResponseDTO;
import com.santt4na.health_check.dto.patientDTO.PatientUpdateDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient/v1")
public class PatientController implements PatientControllerDocs{
	
	@GetMapping(
		produces = {MediaType.APPLICATION_JSON_VALUE}
	)
	@Override
	public ResponseEntity<List<PatientResponseDTO>> findAll() {
		return null;
	}
	
	@GetMapping(value = "/{id}",
		produces = {MediaType.APPLICATION_JSON_VALUE}
	)
	@Override
	public ResponseEntity<PatientResponseDTO> findById(Long id) {
		return null;
	}
	
	@PostMapping(
		produces = {MediaType.APPLICATION_JSON_VALUE},
		consumes = {MediaType.APPLICATION_JSON_VALUE}
	)
	@Override
	public ResponseEntity<PatientResponseDTO> create(PatientCreateDTO person) {
		return null;
	}
	
	@PutMapping(
		produces = {MediaType.APPLICATION_JSON_VALUE},
		consumes = {MediaType.APPLICATION_JSON_VALUE}
	)
	@Override
	public ResponseEntity<PatientResponseDTO> update(PatientUpdateDTO person) {
		return null;
	}
	
	@DeleteMapping(value = "/{id}")
	@Override
	public ResponseEntity<Void> delete(Long id) {
		return null;
	}
}
