package com.santt4na.health_check.controller.docs;

import com.santt4na.health_check.dto.appointmentDTO.AppointmentRequestDTO;
import com.santt4na.health_check.dto.appointmentDTO.AppointmentResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AppointmentControllerDocs {
	
	@Operation(summary = "Adds a new Appointment",
		description = "Adds a new Appointment by passing in a JSON,representation of the Appointment.",
		tags = {"Appointment"},
		responses = {
			@ApiResponse(
				description = "Success",
				responseCode = "200",
				content = @Content(schema = @Schema(implementation = AppointmentResponseDTO.class))
			),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
	})
	ResponseEntity<AppointmentResponseDTO> createAppointment(AppointmentRequestDTO dto);
	
	@Operation(summary = "Updated a Appointment`s information",
		description = "Updated a specific Appointment by your ID",
		tags = {"Appointment"},
		responses = {
			@ApiResponse(
				description = "Success",
				responseCode = "200",
				content = @Content(schema = @Schema(implementation = AppointmentResponseDTO.class))
			),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
		})
	ResponseEntity<AppointmentResponseDTO> updateAppointment(AppointmentRequestDTO dto);
	
	@Operation(summary = "Cancel a Appointment`s ",
		description = "Cancel a specific Appointment by your ID",
		tags = {"Appointment"},
		responses = {
			@ApiResponse(
				description = "Success",
				responseCode = "200",
				content = @Content(schema = @Schema(implementation = AppointmentResponseDTO.class))
			),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
		})
	ResponseEntity<Void> cancelAppointment(Long id);
	
	@Operation(summary = "Find a Appointment`s",
		description = "Find a specific Appointment by your ID",
		tags = {"Appointment"},
		responses = {
			@ApiResponse(
				description = "Success",
				responseCode = "200",
				content = @Content(schema = @Schema(implementation = AppointmentResponseDTO.class))
			),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
		})
	ResponseEntity<AppointmentResponseDTO> findByIdAppointment(Long id);
	
	@Operation(summary = "Find all Appointment",
		description = "Find all Appointment",
		tags = {"Appointment"},
		responses = {
			@ApiResponse(
				description = "Success",
				responseCode = "200",
				content = @Content(schema = @Schema(implementation = AppointmentResponseDTO.class))
			),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
		})
	ResponseEntity<List<AppointmentResponseDTO>> findAllAppointment();
	
	@Operation(summary = "Find Doctor's Appointment",
		description = "Find Doctor's Appointment",
		tags = {"Appointment"},
		responses = {
			@ApiResponse(
				description = "Success",
				responseCode = "200",
				content = @Content(schema = @Schema(implementation = AppointmentResponseDTO.class))
			),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
		})
	ResponseEntity<List<AppointmentResponseDTO>> findByDoctor(Long doctorId);
	
	@Operation(summary = "Find Patient's Appointment",
		description = "Find Patient's Appointment",
		tags = {"Appointment"},
		responses = {
			@ApiResponse(
				description = "Success",
				responseCode = "200",
				content = @Content(schema = @Schema(implementation = AppointmentResponseDTO.class))
			),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
		})
	ResponseEntity<List<AppointmentResponseDTO>> findByPatient(Long patientId);
}
