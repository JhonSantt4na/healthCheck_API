package com.santt4na.health_check.entity;

import com.santt4na.health_check.enums.AppointmentStatus;
import com.santt4na.health_check.enums.CancelledBy;
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

@Setter
@Getter
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Appointment implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 1l;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Appointment date cannot be null")
	private LocalDateTime appointmentDate;
	
	@ManyToOne
	@JoinColumn(name = "patient_id")
	@NotNull(message = "Patient cannot be null")
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name = "doctor_id")
	@NotNull(message = "Doctor cannot be null")
	private Doctor doctor;
	
	@ManyToOne
	@JoinColumn(name = "schedule_id", nullable = false)
	private Schedule schedule;
	
	@NotNull(message = "Status cannot be null")
	@Enumerated(EnumType.STRING)
	private AppointmentStatus status;
	
	@Enumerated(EnumType.STRING)
	private CancelledBy cancelledBy;
	
	@Size(max = 255, message = "Reason must not exceed 255 characters")
	private String reason;
	
	@NotNull(message = "Duration cannot be null")
	private Integer duration;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	private LocalDateTime confirmedAt;

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Appointment that = (Appointment) o;
		return Objects.equals(id, that.id) && Objects.equals(appointmentDate, that.appointmentDate) && Objects.equals(patient, that.patient) && Objects.equals(doctor, that.doctor) && Objects.equals(schedule, that.schedule) && status == that.status && cancelledBy == that.cancelledBy && Objects.equals(reason, that.reason) && Objects.equals(duration, that.duration) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(confirmedAt, that.confirmedAt);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, appointmentDate, patient, doctor, schedule, status, cancelledBy, reason, duration, createdAt, updatedAt, confirmedAt);
	}
}