package com.santt4na.health_check.controller;

import com.santt4na.health_check.controller.docs.AppointmentControllerDocs;
import com.santt4na.health_check.dto.appointmentDTO.AppointmentRequestDTO;
import com.santt4na.health_check.dto.appointmentDTO.AppointmentResponseDTO;
import com.santt4na.health_check.service.AppointmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/appointment/v1")
public class AppointmentController implements AppointmentControllerDocs {
	
	@Autowired
	private AppointmentServiceImpl service;
	
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<AppointmentResponseDTO> createAppointment(@RequestBody AppointmentRequestDTO dto) {
		AppointmentResponseDTO created = service.createAppointment(dto);
		return ResponseEntity
			.created(URI.create("/api/appointments" + created.id()))
			.body(created);
	}
	
	@PutMapping(value = "/{id}",
		produces = MediaType.APPLICATION_JSON_VALUE,
		consumes = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<AppointmentResponseDTO> updateAppointment(@PathVariable Long id, @RequestBody AppointmentRequestDTO dto) {
		AppointmentResponseDTO updated = service.updateAppointment(id, dto);
		return ResponseEntity.ok(updated);
	}
	
	@PutMapping(value = "/cancel/{id}",
		produces = MediaType.APPLICATION_JSON_VALUE,
		consumes = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<Void> cancelAppointment(@PathVariable Long id) {
		service.cancelAppointment(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/{id}",
		produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<AppointmentResponseDTO> findByIdAppointment(@PathVariable Long id) {
		AppointmentResponseDTO found = service.findByIdAppointment(id);
		return ResponseEntity.ok(found);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<List<AppointmentResponseDTO>> findAllAppointment() {
		return ResponseEntity.ok(service.findAllAppointment());
	}
	
	@GetMapping(value = "findDoctor/{doctorId}",
		produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<List<AppointmentResponseDTO>> findByDoctor(@PathVariable Long doctorId) {
		return ResponseEntity.ok(service.findByDoctor(doctorId));
	}
	
	@GetMapping(value = "findPatient/{patientId}",
		produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<List<AppointmentResponseDTO>> findByPatient(@PathVariable Long patientId) {
		return ResponseEntity.ok(service.findByPatient(patientId));
	}
}
