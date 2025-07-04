package com.santt4na.health_check.controller;

import com.santt4na.health_check.controller.docs.DoctorControllerDocs;
import com.santt4na.health_check.dto.doctorDTO.DoctorResponseDTO;
import com.santt4na.health_check.dto.doctorDTO.DoctorUpdateDTO;
import com.santt4na.health_check.service.impl.DoctorServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController implements DoctorControllerDocs{
	
	private final DoctorServiceImpl service;
	
	public DoctorController(DoctorServiceImpl service) {
		this.service = service;
	}
	
	@GetMapping(
		produces = {MediaType.APPLICATION_JSON_VALUE}
	)
	@Override
	public ResponseEntity<List<DoctorResponseDTO>> findAll() {;
		
		return ResponseEntity.ok(service.listAllDoctors());
	}
	
	@GetMapping(value = "/{id}",
		produces = {MediaType.APPLICATION_JSON_VALUE}
	)
	@Override
	public ResponseEntity<DoctorResponseDTO> findById(@PathVariable Long id) {
		
		DoctorResponseDTO found = service.getDoctorById(id);
		return ResponseEntity.ok(found);
	}
	
	@PutMapping(
		value = "/{id}",
		produces = {MediaType.APPLICATION_JSON_VALUE},
		consumes = {MediaType.APPLICATION_JSON_VALUE}
	)
	@Override
	public ResponseEntity<DoctorResponseDTO> update(@PathVariable Long id, @Valid @RequestBody DoctorUpdateDTO doctor) {
		
		DoctorResponseDTO updated = service.updateDoctor(id, doctor);
		return ResponseEntity.ok(updated);
	}
	
	@DeleteMapping(value = "/{id}")
	@Override
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		
		service.deleteDoctor(id);
		return ResponseEntity.noContent().build();
	}
}
