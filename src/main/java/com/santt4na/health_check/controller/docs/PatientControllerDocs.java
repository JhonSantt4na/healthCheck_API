package com.santt4na.health_check.controller.docs;

import com.santt4na.health_check.dto.patientDTO.PatientRequestDTO;
import com.santt4na.health_check.dto.patientDTO.PatientResponseDTO;
import com.santt4na.health_check.dto.patientDTO.PatientUpdateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface PatientControllerDocs {
	
	@Operation(
		summary = "Retrieve all patients",
		description = "Returns a list of all registered patients.",
		tags = {"Patient"},
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "Patients retrieved successfully",
				content = @Content(
					mediaType = MediaType.APPLICATION_JSON_VALUE,
					array = @ArraySchema(schema = @Schema(implementation = PatientResponseDTO.class))
				)
			),
			@ApiResponse(responseCode = "204", description = "No patients found", content = @Content),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
		}
	)
	ResponseEntity<List<PatientResponseDTO>> findAll();
	
	@Operation(
		summary = "Update a patient's information",
		description = "Updates an existing patient's details by their ID.",
		tags = {"Patient"},
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "Patient updated successfully",
				content = @Content(schema = @Schema(implementation = PatientResponseDTO.class))
			),
			@ApiResponse(responseCode = "204", description = "No content", content = @Content),
			@ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
			@ApiResponse(responseCode = "404", description = "Patient not found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
		}
	)
	ResponseEntity<PatientResponseDTO> update(@PathVariable("id") Long id, @RequestBody PatientUpdateDTO patient);
	
	@Operation(
		summary = "Retrieve a patient by ID",
		description = "Returns the details of a patient by their ID.",
		tags = {"Patient"},
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "Patient retrieved successfully",
				content = @Content(schema = @Schema(implementation = PatientResponseDTO.class))
			),
			@ApiResponse(responseCode = "204", description = "No content", content = @Content),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
			@ApiResponse(responseCode = "404", description = "Patient not found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
		}
	)
	ResponseEntity<PatientResponseDTO> findById(@PathVariable("id") Long id);
	
	@Operation(
		summary = "Delete a patient",
		description = "Deletes a patient by their ID.",
		tags = {"Patient"},
		responses = {
			@ApiResponse(responseCode = "200", description = "Patient deleted successfully", content = @Content),
			@ApiResponse(responseCode = "204", description = "No content", content = @Content),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
			@ApiResponse(responseCode = "404", description = "Patient not found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
		}
	)
	ResponseEntity<Void> delete(@PathVariable("id") Long id);
}
