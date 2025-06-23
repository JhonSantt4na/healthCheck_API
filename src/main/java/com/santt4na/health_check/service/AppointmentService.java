package com.santt4na.health_check.service;

import com.santt4na.health_check.dto.scheduleDTO.ScheduleResponseDTO;
import com.santt4na.health_check.dto.appointmentDTO.AppointmentRequestDTO;
import com.santt4na.health_check.dto.appointmentDTO.AppointmentResponseDTO;
import com.santt4na.health_check.dto.appointmentDTO.AppointmentUpdateDTO;
import java.util.List;

public interface AppointmentService {
	
	AppointmentResponseDTO createAppointment(AppointmentRequestDTO dto);
	AppointmentResponseDTO updateAppointment(Long id, AppointmentUpdateDTO dto);
	void cancelAppointment(Long id, String reason);
	AppointmentResponseDTO findByIdAppointment(Long id);
	List<AppointmentResponseDTO> findAllAppointment();
	List<AppointmentResponseDTO> getDoctorAppointments(Long doctorId);
	List<AppointmentResponseDTO> getPatientAppointments(Long patientId);
	AppointmentResponseDTO confirmAppointment(Long id);
	AppointmentResponseDTO cancelAppointmentByDoctor(Long id, String reason);
	AppointmentResponseDTO cancelAppointmentByPatient(Long id, String reason);
	List<ScheduleResponseDTO> getAvailableSchedules(Long doctorId);
	
}