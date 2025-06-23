package com.santt4na.health_check.repository;

import com.santt4na.health_check.entity.Appointment;
import com.santt4na.health_check.entity.Doctor;
import com.santt4na.health_check.entity.Patient;
import com.santt4na.health_check.enums.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	
	@Query("SELECT a FROM Appointment a LEFT JOIN FETCH a.doctor LEFT JOIN FETCH a.patient LEFT JOIN FETCH a.schedule WHERE a.id = :id")
	Optional<Appointment> findByIdWithDetails(@Param("id") Long id);
	
	@Query("SELECT a FROM Appointment a LEFT JOIN FETCH a.doctor LEFT JOIN FETCH a.patient LEFT JOIN FETCH a.schedule")
	List<Appointment> findAllWithDetails();
	
	List<Appointment> findByDoctor_Id(Long doctorId);
	List<Appointment> findByPatient_Id(Long patientId);
	
	List<Appointment> findByStatus(AppointmentStatus status);
	List<Appointment> findByAppointmentDateBetween(LocalDateTime start, LocalDateTime end);
	List<Appointment> findByDoctorAndAppointmentDateBetween(Doctor doctor, LocalDateTime start, LocalDateTime end);
	List<Appointment> findByPatientAndStatus(Patient patient, AppointmentStatus status);
}