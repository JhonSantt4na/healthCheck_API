package com.santt4na.health_check.controller;

import com.santt4na.health_check.dto.scheduleDTO.ScheduleResponseDTO;
import com.santt4na.health_check.dto.appointmentDTO.AppointmentRequestDTO;
import com.santt4na.health_check.dto.appointmentDTO.AppointmentResponseDTO;
import com.santt4na.health_check.dto.appointmentDTO.AppointmentUpdateDTO;
import com.santt4na.health_check.service.AppointmentService;
import com.santt4na.health_check.service.ScheduleService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
	
	private final AppointmentService appointmentService;
	private final ScheduleService scheduleService;
	
	public AppointmentController(AppointmentService appointmentService, ScheduleService scheduleService) {
		this.appointmentService = appointmentService;
		this.scheduleService = scheduleService;
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppointmentResponseDTO> createAppointment(
		@Valid @RequestBody AppointmentRequestDTO dto) {
		
		AppointmentResponseDTO created = appointmentService.createAppointment(dto);
		return ResponseEntity
			.created(URI.create("/api/appointments/" + created.id()))
			.body(created);
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppointmentResponseDTO> updateAppointment(@PathVariable Long id, @Valid @RequestBody AppointmentUpdateDTO dto) {
		AppointmentResponseDTO updated = appointmentService.updateAppointment(id, dto);
		return ResponseEntity.ok(updated);
	}
	
	@PutMapping(value = "/cancel/{id}")
	public ResponseEntity<Void> cancelAppointment(@PathVariable Long id, @RequestParam String reason) {
		appointmentService.cancelAppointment(id, reason);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppointmentResponseDTO> findById(@PathVariable Long id) {
		AppointmentResponseDTO found = appointmentService.findByIdAppointment(id);
		return ResponseEntity.ok(found);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AppointmentResponseDTO>> findAll() {
		List<AppointmentResponseDTO> list = appointmentService.findAllAppointment();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/doctor/{doctorId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AppointmentResponseDTO>> getDoctorAppointments(@PathVariable Long doctorId) {
		List<AppointmentResponseDTO> list = appointmentService.getDoctorAppointments(doctorId);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/patient/{patientId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AppointmentResponseDTO>> getPatientAppointments(@PathVariable Long patientId) {
		List<AppointmentResponseDTO> list = appointmentService.getPatientAppointments(patientId);
		return ResponseEntity.ok(list);
	}
	
	@PutMapping(value = "/{id}/confirm", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppointmentResponseDTO> confirmAppointment(@PathVariable Long id) {
		AppointmentResponseDTO confirmed = appointmentService.confirmAppointment(id);
		return ResponseEntity.ok(confirmed);
	}
	
	@PutMapping(value = "/{id}/cancel/doctor", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppointmentResponseDTO> cancelByDoctor(@PathVariable Long id, @RequestParam String reason) {
		AppointmentResponseDTO cancelled = appointmentService.cancelAppointmentByDoctor(id, reason);
		return ResponseEntity.ok(cancelled);
	}
	
	@PutMapping(value = "/{id}/cancel/patient", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppointmentResponseDTO> cancelByPatient(@PathVariable Long id, @RequestParam String reason) {
		AppointmentResponseDTO cancelled = appointmentService.cancelAppointmentByPatient(id, reason);
		return ResponseEntity.ok(cancelled);
	}
	
	@GetMapping(value = "/doctor/{doctorId}/available-schedules", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ScheduleResponseDTO>> getAvailableSchedules(@PathVariable Long doctorId) {
		List<ScheduleResponseDTO> available = scheduleService.findByDoctorId(doctorId);
		return ResponseEntity.ok(available);
	}
}