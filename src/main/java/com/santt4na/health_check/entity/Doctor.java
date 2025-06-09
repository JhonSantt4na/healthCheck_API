package com.santt4na.health_check.entity;

import com.santt4na.health_check.entity.security.User;
import com.santt4na.health_check.enums.Gender;
import com.santt4na.health_check.enums.Roles;
import com.santt4na.health_check.enums.Specialty;
import com.santt4na.health_check.enums.UserState;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Doctor extends User {
	
	@NotNull(message = "Name cannot be null")
	@Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
	private String name;
	
	@NotNull(message = "Phone cannot be null")
	@Pattern(regexp = "\\+?\\d{2}\\s?\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}", message = "Invalid phone format")
	private String phone;
	
	@NotNull(message = "Specialty cannot be null")
	@Enumerated(EnumType.STRING)
	private Specialty specialty;
	
	@NotNull(message = "Gender cannot be null")
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Column(unique = true, nullable = false)
	@NotNull(message = "Medical license cannot be null")
	@Pattern(regexp = "\\d{1,6}-[A-Z]{2}", message = "Medical license must follow the format 123456-UF")
	private String medicalLicense; // CRM
	
	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Appointment> appointments = new ArrayList<>();
	
	public Doctor() {
	}
	
	public Doctor(Long id, String email, String password, Roles role, UserState status, LocalDateTime createdAt, LocalDateTime updatedAt, boolean accountActive, String name, String phone, Specialty specialty, Gender gender, String medicalLicense, List<Appointment> appointments) {
		super(id, email, password, role, status, createdAt, updatedAt, accountActive);
		this.name = name;
		this.phone = phone;
		this.specialty = specialty;
		this.gender = gender;
		this.medicalLicense = medicalLicense;
		this.appointments = appointments;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
	
	public Gender getGender() {
		return gender;
	}
	
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public String getMedicalLicense() {
		return medicalLicense;
	}
	
	public void setMedicalLicense(String medicalLicense) {
		this.medicalLicense = medicalLicense;
	}
	
	public List<Appointment> getAppointments() {
		return appointments;
	}
	
	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Doctor doctor = (Doctor) o;
		return Objects.equals(name, doctor.name) && Objects.equals(phone, doctor.phone) && specialty == doctor.specialty && gender == doctor.gender && Objects.equals(medicalLicense, doctor.medicalLicense) && Objects.equals(appointments, doctor.appointments);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name, phone, specialty, gender, medicalLicense, appointments);
	}
}
