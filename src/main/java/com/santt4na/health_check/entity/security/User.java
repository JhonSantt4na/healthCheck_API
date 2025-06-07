package com.santt4na.health_check.entity.security;

import com.santt4na.health_check.enums.Roles;
import com.santt4na.health_check.enums.UserState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_db")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)
	@NotNull(message = "Email cannot be null")
	@Email(message = "Invalid email format")
	private String email;
	
	@NotNull(message = "Password cannot be null")
	@Size(min = 8, message = "Password must be at least 8 characters")
	private String password;
	
	@NotNull(message = "Role cannot be null")
	@Enumerated(EnumType.STRING)
	private Roles role;
	
	@NotNull(message = "Status cannot be null")
	@Enumerated(EnumType.STRING)
	private UserState status;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	private boolean accountActive;
	
}
