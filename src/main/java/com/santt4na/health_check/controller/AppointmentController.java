package com.santt4na.health_check.controller;

import com.santt4na.health_check.controller.docs.AppointmentControllerDocs;
import com.santt4na.health_check.dto.appointmentDTO.AppointmentRequestDTO;
import com.santt4na.health_check.dto.appointmentDTO.AppointmentResponseDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/appointment/v1")
public class AppointmentController implements AppointmentControllerDocs {
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<AppointmentResponseDTO> createAppointment(@RequestBody AppointmentRequestDTO dto) {
		return null;
	}
	
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<AppointmentResponseDTO> updateAppointment(@RequestBody AppointmentRequestDTO dto) {
		return null;
	}
	
	@PutMapping(value = "/cancel", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<Void> cancelAppointment(@RequestParam Long id) {
		return null;
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<AppointmentResponseDTO> findByIdAppointment(@PathVariable Long id) {
		return null;
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<List<AppointmentResponseDTO>> findAllAppointment() {
		return null;
	}
	
	@GetMapping(value = "/doctor", produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<List<AppointmentResponseDTO>> findByDoctor(@RequestParam Long doctorId) {
		return null;
	}
	
	@GetMapping(value = "/patient", produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<List<AppointmentResponseDTO>> findByPatient(@RequestParam Long patientId) {
		return null;
	}
}
