package com.santt4na.health_check.controller.docs;

import com.santt4na.health_check.dto.patientDTO.PatientCreateDTO;
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
	
	@Operation(summary = "Find All Patient",
		description = "Find All Patient",
		tags = {"Patient"},
		responses = {
			@ApiResponse(
				description = "Success",
				responseCode = "200",
				content = {
					@Content(
						mediaType = MediaType.APPLICATION_JSON_VALUE,
						array = @ArraySchema(schema = @Schema(implementation = PatientResponseDTO.class))
					)
				}
			),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
		})
	ResponseEntity<List<PatientResponseDTO>> findAll();
	
	@Operation(summary = "Finds a Patient",
		description = "Find a specific Patient by your ID",
		tags = {"Patient"},
		responses = {
			@ApiResponse(
				description = "Success",
				responseCode = "200",
				content = @Content(schema = @Schema(implementation = PatientResponseDTO.class))
			),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
		})
	ResponseEntity<PatientResponseDTO> findById(@PathVariable("id") Long id);
	
	@Operation(summary = "Adds a new Patient",
		description = "Adds a new Patient by passing in a JSON,representation of the Patient.",
		tags = {"Patient"},
		responses = {
			@ApiResponse(
				description = "Success",
				responseCode = "200",
				content = @Content(schema = @Schema(implementation = PatientResponseDTO.class))
			),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
		})
	ResponseEntity<PatientResponseDTO> create(@RequestBody PatientCreateDTO person);
	
	@Operation(summary = "Updated a Patient`s information",
		description = "Updated a specific Patient by your ID",
		tags = {"Patient"},
		responses = {
			@ApiResponse(
				description = "Success",
				responseCode = "200",
				content = @Content(schema = @Schema(implementation = PatientResponseDTO.class))
			),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
		})
	ResponseEntity<PatientResponseDTO> update(@RequestBody PatientUpdateDTO person);
	
	@Operation(summary = "Delete a Patient",
		description = "Deletes a specific Patient by their ID",
		tags = {"Patient"},
		responses = {
			@ApiResponse(
				description = "Success",
				responseCode = "200",
				content = @Content(schema = @Schema(implementation = PatientResponseDTO.class))
			),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
		})
	ResponseEntity<Void> delete(@PathVariable("id") Long id);
}
