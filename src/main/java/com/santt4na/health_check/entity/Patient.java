package com.santt4na.health_check.entity;

import com.santt4na.health_check.entity.security.User;
import com.santt4na.health_check.enums.Gender;
import com.santt4na.health_check.enums.Roles;
import com.santt4na.health_check.enums.UserState;
import jakarta.persistence.*;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Patient extends User {
	
	@NotNull(message = "Name cannot be null")
	@Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
	private String name;
	
	@Past(message = "Date of birth must be in the past")
	private LocalDate dateOfBirth;
	
	@Column(unique = true)
	@NotNull(message = "CPF cannot be null")
	@Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}", message = "Invalid CPF format")
	private String cpf;
	
	@NotNull(message = "Phone cannot be null")
	@Pattern(regexp = "\\+?\\d{2}\\s?\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}", message = "Invalid phone format")
	private String phone;
	
	@Size(max = 50, message = "Health insurance must not exceed 50 characters")
	private String healthInsurance;
	
	@NotNull(message = "Gender cannot be null")
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Appointment> appointments = new ArrayList<>();
	
	public Patient() {
	}
	
	public Patient(Long id, String email, String password, Roles role, UserState status, LocalDateTime createdAt, LocalDateTime updatedAt, boolean accountActive, String name, LocalDate dateOfBirth, String cpf, String phone, String healthInsurance, Gender gender, List<Appointment> appointments) {
		super(id, email, password, role, status, createdAt, updatedAt, accountActive);
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.cpf = cpf;
		this.phone = phone;
		this.healthInsurance = healthInsurance;
		this.gender = gender;
		this.appointments = appointments;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getHealthInsurance() {
		return healthInsurance;
	}
	
	public void setHealthInsurance(String healthInsurance) {
		this.healthInsurance = healthInsurance;
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public void setGender(Gender gender) {
		this.gender = gender;
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
		Patient patient = (Patient) o;
		return Objects.equals(name, patient.name) && Objects.equals(dateOfBirth, patient.dateOfBirth) && Objects.equals(cpf, patient.cpf) && Objects.equals(phone, patient.phone) && Objects.equals(healthInsurance, patient.healthInsurance) && gender == patient.gender && Objects.equals(appointments, patient.appointments);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name, dateOfBirth, cpf, phone, healthInsurance, gender, appointments);
	}
}
