package com.santt4na.health_check.repository;

import com.santt4na.health_check.entity.Appointment;
import com.santt4na.health_check.entity.Doctor;
import com.santt4na.health_check.entity.Patient;
import com.santt4na.health_check.enums.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	
	List<Appointment> findByDoctor(Doctor doctor);
	List<Appointment> findByPatient(Patient patient);
	List<Appointment> findByStatus(AppointmentStatus status);
	List<Appointment> findByAppointmentDateBetween(LocalDateTime start, LocalDateTime end);
	List<Appointment> findByDoctorAndAppointmentDateBetween(Doctor doctor, LocalDateTime start, LocalDateTime end);
	List<Appointment> findByPatientAndStatus(Patient patient, AppointmentStatus status);
}