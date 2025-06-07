package com.santt4na.health_check.entity;

import com.santt4na.health_check.entity.security.User;
import com.santt4na.health_check.enums.Gender;
import com.santt4na.health_check.enums.Roles;
import com.santt4na.health_check.enums.Specialty;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
	private String medicalLicense;
	
	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Appointment> appointments = new ArrayList<>();
}
