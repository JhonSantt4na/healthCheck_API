package com.santt4na.health_check.controller.docs;

import com.santt4na.health_check.dto.appointmentDTO.AppointmentRequestDTO;
import com.santt4na.health_check.dto.appointmentDTO.AppointmentResponseDTO;
import com.santt4na.health_check.dto.appointmentDTO.AppointmentUpdateDTO;
import com.santt4na.health_check.dto.scheduleDTO.ScheduleRequestDTO;
import com.santt4na.health_check.dto.scheduleDTO.ScheduleResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface AppointmentControllerDocs {
	
	@Operation(
		summary = "Create a new appointment",
		description = "Creates an appointment with the provided details.",
		tags = {"Appointment"},
		responses = {
			@ApiResponse(responseCode = "201", description = "Appointment created successfully", content = @Content(schema = @Schema(implementation = AppointmentResponseDTO.class))),
			@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
		}
	)
	ResponseEntity<AppointmentResponseDTO> createAppointment(@RequestBody AppointmentRequestDTO dto);
	
	@Operation(
		summary = "Update an existing appointment",
		description = "Updates the information of an appointment by ID.",
		tags = {"Appointment"},
		responses = {
			@ApiResponse(responseCode = "200", description = "Updated successfully", content = @Content(schema = @Schema(implementation = AppointmentResponseDTO.class))),
			@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
			@ApiResponse(responseCode = "404", description = "Appointment not found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
		}
	)
	ResponseEntity<AppointmentResponseDTO> updateAppointment(@PathVariable Long id, @Valid @RequestBody AppointmentUpdateDTO dto);
	
	@Operation(
		summary = "Cancel appointment with description",
		description = "Cancels an appointment providing a reason.",
		tags = {"Appointment"},
		responses = {
			@ApiResponse(responseCode = "204", description = "Cancelled successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid ID or data", content = @Content),
			@ApiResponse(responseCode = "404", description = "Appointment not found", content = @Content)
		}
	)
	ResponseEntity<Void> cancelAppointment(@PathVariable Long id, @RequestBody String description);
	
	@Operation(
		summary = "Get appointment by ID",
		description = "Returns a specific appointment by its ID.",
		tags = {"Appointment"},
		responses = {
			@ApiResponse(responseCode = "200", description = "Appointment found", content = @Content(schema = @Schema(implementation = AppointmentResponseDTO.class))),
			@ApiResponse(responseCode = "404", description = "Appointment not found", content = @Content)
		}
	)
	ResponseEntity<AppointmentResponseDTO> findById(@PathVariable Long id);
	
	@Operation(
		summary = "Get all appointments",
		description = "Returns all appointments in the system.",
		tags = {"Appointment"},
		responses = {
			@ApiResponse(responseCode = "200", description = "List of appointments", content = @Content(schema = @Schema(implementation = AppointmentResponseDTO.class)))
		}
	)
	ResponseEntity<List<AppointmentResponseDTO>> findAll();
	
	@Operation(
		summary = "Get appointments by doctor ID",
		description = "Returns all appointments for a specific doctor.",
		tags = {"Appointment"},
		responses = {
			@ApiResponse(responseCode = "200", description = "Appointments found", content = @Content(schema = @Schema(implementation = AppointmentResponseDTO.class))),
			@ApiResponse(responseCode = "404", description = "Doctor not found", content = @Content)
		}
	)
	ResponseEntity<List<AppointmentResponseDTO>> getDoctorAppointments(@PathVariable Long doctorId);
	
	@Operation(
		summary = "Get appointments by patient ID",
		description = "Returns all appointments for a specific patient.",
		tags = {"Appointment"},
		responses = {
			@ApiResponse(responseCode = "200", description = "Appointments found", content = @Content(schema = @Schema(implementation = AppointmentResponseDTO.class))),
			@ApiResponse(responseCode = "404", description = "Patient not found", content = @Content)
		}
	)
	ResponseEntity<List<AppointmentResponseDTO>> getPatientAppointments(@PathVariable Long patientId);
	
	@Operation(
		summary = "Confirm appointment",
		description = "Confirms an appointment by its ID.",
		tags = {"Appointment"},
		responses = {
			@ApiResponse(responseCode = "200", description = "Appointment confirmed", content = @Content(schema = @Schema(implementation = AppointmentResponseDTO.class))),
			@ApiResponse(responseCode = "404", description = "Appointment not found", content = @Content)
		}
	)
	ResponseEntity<AppointmentResponseDTO> confirmAppointment(@PathVariable Long id);
	
	@Operation(
		summary = "Cancel appointment by doctor",
		description = "Cancels an appointment initiated by the doctor.",
		tags = {"Appointment"},
		responses = {
			@ApiResponse(responseCode = "200", description = "Appointment cancelled", content = @Content(schema = @Schema(implementation = AppointmentResponseDTO.class))),
			@ApiResponse(responseCode = "404", description = "Appointment not found", content = @Content)
		}
	)
	ResponseEntity<AppointmentResponseDTO> cancelByDoctor(@PathVariable Long id, @RequestParam String reason);
	
	@Operation(
		summary = "Cancel appointment by patient",
		description = "Cancels an appointment initiated by the patient.",
		tags = {"Appointment"},
		responses = {
			@ApiResponse(responseCode = "200", description = "Appointment cancelled", content = @Content(schema = @Schema(implementation = AppointmentResponseDTO.class))),
			@ApiResponse(responseCode = "404", description = "Appointment not found", content = @Content)
		}
	)
	ResponseEntity<AppointmentResponseDTO> cancelByPatient(@PathVariable Long id, @RequestParam String reason);
	
	@Operation(
		summary = "List available schedules for a doctor",
		description = "Returns all available schedules for a given doctor.",
		tags = {"Schedule"},
		responses = {
			@ApiResponse(responseCode = "200", description = "Schedules found", content = @Content(schema = @Schema(implementation = ScheduleRequestDTO.class))),
			@ApiResponse(responseCode = "404", description = "Doctor not found", content = @Content)
		}
	)
	ResponseEntity<List<ScheduleResponseDTO>> getAvailableSchedules(@PathVariable Long doctorId);
}
