package com.santt4na.health_check.entity;


import com.santt4na.health_check.entity.security.User;
import com.santt4na.health_check.enums.Gender;
import com.santt4na.health_check.enums.Roles;
import com.santt4na.health_check.enums.UserState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}
