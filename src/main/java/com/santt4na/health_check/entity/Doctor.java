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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
	
	public Doctor(Long id, String email, String password, Roles role, UserState status, LocalDateTime createdAt, LocalDateTime updatedAt, boolean accountActive, String name, String phone, Specialty specialty, Gender gender, String medicalLicense, List<Appointment> appointments) {
		super(id, email, password, role, status, createdAt, updatedAt, accountActive);
		this.name = name;
		this.phone = phone;
		this.specialty = specialty;
		this.gender = gender;
		this.medicalLicense = medicalLicense;
		this.appointments = appointments;
	}
}
