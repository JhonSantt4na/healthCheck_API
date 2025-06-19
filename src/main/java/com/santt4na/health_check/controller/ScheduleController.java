package com.santt4na.health_check.controller;

import com.santt4na.health_check.controller.docs.ScheduleControllerDocs;
import com.santt4na.health_check.dto.scheduleDTO.ScheduleRequestDTO;
import com.santt4na.health_check.dto.scheduleDTO.ScheduleResponseDTO;
import com.santt4na.health_check.dto.scheduleDTO.ScheduleUpdateDTO;
import com.santt4na.health_check.service.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController implements ScheduleControllerDocs {
	
	@Autowired
	private ScheduleService service;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ScheduleResponseDTO>> findAll() {
		List<ScheduleResponseDTO> list = service.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ScheduleResponseDTO> findById(@PathVariable Long id) {
		ScheduleResponseDTO found = service.findById(id);
		return ResponseEntity.ok(found);
	}
	
	@PostMapping(
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<ScheduleResponseDTO> create(@Valid @RequestBody ScheduleRequestDTO dto) {
		ScheduleResponseDTO created = service.createSchedule(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
	
	@PutMapping(value = "/{id}",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ScheduleResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ScheduleUpdateDTO dto) {
		ScheduleResponseDTO updated = service.updateSchedule(id, dto);
		return ResponseEntity.ok(updated);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.deleteSchedule(id);
		return ResponseEntity.noContent().build();
	}
}
