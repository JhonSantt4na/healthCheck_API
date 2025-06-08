package com.santt4na.health_check.entity;

import com.santt4na.health_check.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Appointment{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Appointment date cannot be null")
	private LocalDateTime appointmentDate;
	
	private Integer duration;
	
	@Size(max = 255, message = "Reason must not exceed 255 characters")
	private String reason;
	
	@ManyToOne
	@JoinColumn(name = "patient_id")
	@NotNull(message = "Patient cannot be null")
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name = "doctor_id")
	@NotNull(message = "Doctor cannot be null")
	private Doctor doctor;
	
	@NotNull(message = "Status cannot be null")
	@Enumerated(EnumType.STRING)
	private AppointmentStatus status;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	public Appointment(Long id, LocalDateTime appointmentDate, Integer duration, String reason, Patient patient, Doctor doctor, AppointmentStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.appointmentDate = appointmentDate;
		this.duration = duration;
		this.reason = reason;
		this.patient = patient;
		this.doctor = doctor;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
}