package com.santt4na.health_check.controller.docs;

import com.santt4na.health_check.dto.doctorDTO.DoctorResponseDTO;
import com.santt4na.health_check.dto.doctorDTO.DoctorUpdateDTO;
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

public interface DoctorControllerDocs {
	
	@Operation(
		summary = "List all doctors",
		description = "Returns a list of all registered doctors.",
		tags = {"Doctor"},
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "Success",
				content = @Content(
					mediaType = MediaType.APPLICATION_JSON_VALUE,
					array = @ArraySchema(schema = @Schema(implementation = DoctorResponseDTO.class))
				)
			),
			@ApiResponse(responseCode = "204", description = "No content", content = @Content),
			@ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
		}
	)
	ResponseEntity<List<DoctorResponseDTO>> findAll();
	
	@Operation(
		summary = "Find doctor by ID",
		description = "Returns the data of a specific doctor based on the given ID.",
		tags = {"Doctor"},
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "Success",
				content = @Content(schema = @Schema(implementation = DoctorResponseDTO.class))
			),
			@ApiResponse(responseCode = "204", description = "No content", content = @Content),
			@ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
		}
	)
	ResponseEntity<DoctorResponseDTO> findById(@PathVariable("id") Long id);
	
	@Operation(
		summary = "Update doctor",
		description = "Updates the data of an existing doctor based on the ID.",
		tags = {"Doctor"},
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "Success",
				content = @Content(schema = @Schema(implementation = DoctorResponseDTO.class))
			),
			@ApiResponse(responseCode = "204", description = "No content", content = @Content),
			@ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
		}
	)
	ResponseEntity<DoctorResponseDTO> update(@PathVariable("id") Long id, @RequestBody DoctorUpdateDTO doctor);
	
	@Operation(
		summary = "Delete doctor",
		description = "Removes a doctor from the system based on the provided ID.",
		tags = {"Doctor"},
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "Success",
				content = @Content(schema = @Schema(implementation = DoctorResponseDTO.class))
			),
			@ApiResponse(responseCode = "204", description = "No content", content = @Content),
			@ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
		}
	)
	ResponseEntity<Void> delete(@PathVariable("id") Long id);
}
