package com.santt4na.health_check.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "schedules")
@NoArgsConstructor
@Setter
@Getter
public class Schedule implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doctor_id", nullable = false)
	private Doctor doctor;
	
	@OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Appointment> appointments = new ArrayList<>();
	
	@Column(nullable = false)
	private LocalDateTime startTime;
	
	@Column(nullable = false)
	private LocalDateTime endTime;
	
	@Column(nullable = false)
	private Boolean available = true;
	
	@PrePersist
	@PreUpdate
	private void validateTimes() {
		if (!endTime.isAfter(startTime)) {
			throw new IllegalStateException("End time must be after start time");
		}
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Schedule schedule = (Schedule) o;
		return Objects.equals(id, schedule.id) && Objects.equals(doctor, schedule.doctor) && Objects.equals(appointments, schedule.appointments) && Objects.equals(startTime, schedule.startTime) && Objects.equals(endTime, schedule.endTime) && Objects.equals(available, schedule.available);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, doctor, appointments, startTime, endTime, available);
	}
}
