package com.santt4na.health_check.controller.docs;

import com.santt4na.health_check.dto.scheduleDTO.ScheduleRequestDTO;
import com.santt4na.health_check.dto.scheduleDTO.ScheduleResponseDTO;
import com.santt4na.health_check.dto.scheduleDTO.ScheduleUpdateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Schedule", description = "Operations for managing doctor schedules")
public interface ScheduleControllerDocs {
	
	@Operation(summary = "List all schedules", description = "Retrieve a list of all schedules")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Schedules retrieved successfully")
	})
	ResponseEntity<List<ScheduleResponseDTO>> findAll();
	
	@Operation(summary = "Get schedule by ID", description = "Retrieve details of a single schedule by its ID")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Schedule found"),
		@ApiResponse(responseCode = "404", description = "Schedule not found")
	})
	ResponseEntity<ScheduleResponseDTO> findById(@Parameter(description = "ID of the schedule to retrieve", required = true) Long id);
	
	@Operation(summary = "Create schedule", description = "Create a new schedule for a given doctor")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Schedule created successfully"),
		@ApiResponse(responseCode = "400", description = "Invalid input data")
	})
	ResponseEntity<ScheduleResponseDTO> create(@Parameter(description = "Schedule details to create", required = true) ScheduleRequestDTO dto);
	
	@Operation(summary = "Update schedule", description = "Update an existing schedule by ID")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Schedule updated successfully"),
		@ApiResponse(responseCode = "400", description = "Invalid input data"),
		@ApiResponse(responseCode = "404", description = "Schedule not found")
	})
	ResponseEntity<ScheduleResponseDTO> update(
		@Parameter(description = "ID of the schedule to update", required = true) Long id,
		@Parameter(description = "Updated schedule data", required = true) ScheduleUpdateDTO dto
	);
	
	@Operation(summary = "Delete schedule", description = "Delete a schedule by its ID")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Schedule deleted successfully"),
		@ApiResponse(responseCode = "404", description = "Schedule not found")
	})
	ResponseEntity<Void> delete(
		@Parameter(description = "ID of the schedule to delete", required = true) Long id
	);
}
