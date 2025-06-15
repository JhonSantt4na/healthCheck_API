package com.santt4na.health_check.entity;

import com.santt4na.health_check.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@ToString
@AllArgsConstructor
public class Appointment implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 1l;
	
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
	
	public Appointment() {
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public LocalDateTime getAppointmentDate() {
		return appointmentDate;
	}
	
	public void setAppointmentDate(LocalDateTime appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	
	public Integer getDuration() {
		return duration;
	}
	
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
	public String getReason() {
		return reason;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public Patient getPatient() {
		return patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public Doctor getDoctor() {
		return doctor;
	}
	
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	public AppointmentStatus getStatus() {
		return status;
	}
	
	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Appointment that = (Appointment) o;
		return Objects.equals(id, that.id) && Objects.equals(appointmentDate, that.appointmentDate) && Objects.equals(duration, that.duration) && Objects.equals(reason, that.reason) && Objects.equals(patient, that.patient) && Objects.equals(doctor, that.doctor) && status == that.status && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, appointmentDate, duration, reason, patient, doctor, status, createdAt, updatedAt);
	}
}