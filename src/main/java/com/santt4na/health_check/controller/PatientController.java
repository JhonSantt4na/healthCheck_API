package com.santt4na.health_check.controller;

import com.santt4na.health_check.controller.docs.PatientControllerDocs;
import com.santt4na.health_check.dto.patientDTO.PatientRequestDTO;
import com.santt4na.health_check.dto.patientDTO.PatientResponseDTO;
import com.santt4na.health_check.dto.patientDTO.PatientUpdateDTO;
import com.santt4na.health_check.service.impl.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientController implements PatientControllerDocs{
	
	@Autowired
	private PatientServiceImpl service;
	
	@GetMapping(
		produces = {MediaType.APPLICATION_JSON_VALUE}
	)
	@Override
	public ResponseEntity<List<PatientResponseDTO>> findAll() {
		return ResponseEntity.ok(service.listAllPatient());
	}
	
	@GetMapping(value = "/{id}",
		produces = {MediaType.APPLICATION_JSON_VALUE}
	)
	@Override
	public ResponseEntity<PatientResponseDTO> findById(@PathVariable  Long id) {
		PatientResponseDTO found = service.getPatientById(id);
		return ResponseEntity.ok(found);
	}
	
	@PutMapping(
		value = "/{id}",
		produces = {MediaType.APPLICATION_JSON_VALUE},
		consumes = {MediaType.APPLICATION_JSON_VALUE}
	)
	@Override
	public ResponseEntity<PatientResponseDTO> update(@PathVariable Long id, @RequestBody PatientUpdateDTO patient) {
		PatientResponseDTO updated = service.updatePatient(id, patient);
		return ResponseEntity.ok(updated);
	}
	
	@DeleteMapping(value = "/{id}")
	@Override
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.deletePatient(id);
		return ResponseEntity.noContent().build();
	}
}
