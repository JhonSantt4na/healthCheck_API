package com.santt4na.health_check.dto.doctorDTO;

import com.santt4na.health_check.enums.Gender;
import com.santt4na.health_check.enums.Specialty;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public record DoctorRequestDTO(
	
	@Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters")
	@NotNull(message = "Full name is required") String fullName,
	
	@Email(message = "Email must be valid") @NotNull(message = "Email is required") String email,
	
	@NotNull(message = "Gender is required") Gender gender,
	
	@Pattern(
		regexp = "\\+?\\d{2}\\s?\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}",
		message = "Phone must follow the pattern: +55 (11) 91234-5678")
	@NotNull(message = "Phone is required") String phone,
	
	@NotNull(message = "Specialty is required") Specialty specialty,
	
	@Pattern(
		regexp = "\\d{1,6}-[A-Z]{2}",
		message = "Medical license must follow the pattern: 123456-AB"
	)
	@NotNull(message = "Medical license is required") String medicalLicense
	
) {}