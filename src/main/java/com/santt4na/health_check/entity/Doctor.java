package com.santt4na.health_check.entity;

import com.santt4na.health_check.enums.Gender;
import com.santt4na.health_check.enums.Specialty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@ToString
@AllArgsConstructor
public class Doctor implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Name cannot be null")
	@Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
	private String fullName;
	
	@Email
	@NotNull(message = "Email cannot be null")
	@Column(nullable = false, unique = true)
	private String email;
	
	@NotNull(message = "Gender cannot be null")
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@NotNull(message = "Phone cannot be null")
	@Pattern(regexp = "\\+?\\d{2}\\s?\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}", message = "Invalid phone format")
	private String phone;
	
	@NotNull(message = "Specialty cannot be null")
	@Enumerated(EnumType.STRING)
	private Specialty specialty;
	
	@Column(unique = true, nullable = false)
	@NotNull(message = "Medical license cannot be null")
	@Pattern(regexp = "\\d{1,6}-[A-Z]{2}", message = "Medical license must follow the format 123456-UF")
	private String medicalLicense; // CRM
	
	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Schedule> availableSchedules = new ArrayList<>();
	
	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
	@ToString.Exclude
	private List<Appointment> appointments = new ArrayList<>();
	
	/*
	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = true)
	private User user;
	*/
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	public Doctor() {
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Specialty getSpecialty() {
		return specialty;
	}
	
	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}
	
	public String getMedicalLicense() {
		return medicalLicense;
	}
	
	public void setMedicalLicense(String medicalLicense) {
		this.medicalLicense = medicalLicense;
	}
	
	public List<Schedule> getAvailableSchedules() {
		return availableSchedules;
	}
	
	public void setAvailableSchedules(List<Schedule> availableSchedules) {
		this.availableSchedules = availableSchedules;
	}
	
	public List<Appointment> getAppointments() {
		return appointments;
	}
	
	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
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
		Doctor doctor = (Doctor) o;
		return Objects.equals(id, doctor.id) && Objects.equals(fullName, doctor.fullName) && Objects.equals(email, doctor.email) && gender == doctor.gender && Objects.equals(phone, doctor.phone) && specialty == doctor.specialty && Objects.equals(medicalLicense, doctor.medicalLicense) && Objects.equals(availableSchedules, doctor.availableSchedules) && Objects.equals(appointments, doctor.appointments) && Objects.equals(createdAt, doctor.createdAt) && Objects.equals(updatedAt, doctor.updatedAt);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, fullName, email, gender, phone, specialty, medicalLicense, availableSchedules, appointments, createdAt, updatedAt);
	}
}
