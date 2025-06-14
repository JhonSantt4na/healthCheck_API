package com.santt4na.health_check.controller;

import com.santt4na.health_check.controller.docs.DoctorControllerDocs;
import com.santt4na.health_check.dto.doctorDTO.DoctorRequestDTO;
import com.santt4na.health_check.dto.doctorDTO.DoctorResponseDTO;
import com.santt4na.health_check.dto.doctorDTO.DoctorUpdateDTO;
import com.santt4na.health_check.service.DoctorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/doctor/v1")
public class DoctorController implements DoctorControllerDocs{
	
	@Autowired
	private DoctorServiceImpl service;
	
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
	
	@PostMapping(
		produces = {MediaType.APPLICATION_JSON_VALUE},
		consumes = {MediaType.APPLICATION_JSON_VALUE}
	)
	@Override
	public ResponseEntity<DoctorResponseDTO> create(@RequestBody DoctorRequestDTO doctor) {
		DoctorResponseDTO created = service.createDoctor(doctor);
		return ResponseEntity
			.created(URI.create("/api/doctor/" + created.id()))
			.body(created);
	}
	
	@PutMapping(
		value = "/{id}",
		produces = {MediaType.APPLICATION_JSON_VALUE},
		consumes = {MediaType.APPLICATION_JSON_VALUE}
	)
	@Override
	public ResponseEntity<DoctorResponseDTO> update(@PathVariable Long id, @RequestBody DoctorUpdateDTO doctor) {
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
