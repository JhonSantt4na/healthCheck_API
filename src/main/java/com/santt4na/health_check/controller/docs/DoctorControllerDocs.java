package com.santt4na.health_check.controller.docs;

import com.santt4na.health_check.dto.doctorDTO.DoctorCreateDTO;
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
	
	@Operation(summary = "Find All Doctor",
		description = "Find All Doctor",
		tags = {"Doctor"},
		responses = {
			@ApiResponse(
				description = "Success",
				responseCode = "200",
				content = {
					@Content(
						mediaType = MediaType.APPLICATION_JSON_VALUE,
						array = @ArraySchema(schema = @Schema(implementation = DoctorResponseDTO.class))
					)
				}
			),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
		})
	ResponseEntity<List<DoctorResponseDTO>> findAll();
	
	@Operation(summary = "Finds a Doctor",
		description = "Find a specific doctor by your ID",
		tags = {"Doctor"},
		responses = {
			@ApiResponse(
				description = "Success",
				responseCode = "200",
				content = @Content(schema = @Schema(implementation = DoctorResponseDTO.class))
			),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
		})
	ResponseEntity<DoctorResponseDTO> findById(@PathVariable("id") Long id);
	
	@Operation(summary = "Adds a new Doctor",
		description = "Adds a new doctor by passing in a JSON,representation of the Doctor.",
		tags = {"Doctor"},
		responses = {
			@ApiResponse(
				description = "Success",
				responseCode = "200",
				content = @Content(schema = @Schema(implementation = DoctorResponseDTO.class))
			),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
		})
	ResponseEntity<DoctorResponseDTO> create(@RequestBody DoctorCreateDTO person);
	
	@Operation(summary = "Updated a doctor`s information",
		description = "Updated a specific doctor by your ID",
		tags = {"Doctor"},
		responses = {
			@ApiResponse(
				description = "Success",
				responseCode = "200",
				content = @Content(schema = @Schema(implementation = DoctorResponseDTO.class))
			),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
		})
	ResponseEntity<DoctorResponseDTO> update(@RequestBody DoctorUpdateDTO person);
	
	@Operation(summary = "Delete a Doctor",
		description = "Deletes a specific doctor by their ID",
		tags = {"Doctor"},
		responses = {
			@ApiResponse(
				description = "Success",
				responseCode = "200",
				content = @Content(schema = @Schema(implementation = DoctorResponseDTO.class))
			),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
		})
	ResponseEntity<Void> delete(@PathVariable("id") Long id);
}
