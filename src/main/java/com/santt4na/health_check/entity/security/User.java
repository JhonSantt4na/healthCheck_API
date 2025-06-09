package com.santt4na.health_check.entity.security;

import com.santt4na.health_check.enums.Roles;
import com.santt4na.health_check.enums.UserState;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
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
	
	public User() {
	}
	
	public User(Long id, String email, String password, Roles role, UserState status, LocalDateTime createdAt, LocalDateTime updatedAt, boolean accountActive) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.role = role;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.accountActive = accountActive;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Roles getRole() {
		return role;
	}
	
	public void setRole(Roles role) {
		this.role = role;
	}
	
	public UserState getStatus() {
		return status;
	}
	
	public void setStatus(UserState status) {
		this.status = status;
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
	
	public boolean isAccountActive() {
		return accountActive;
	}
	
	public void setAccountActive(boolean accountActive) {
		this.accountActive = accountActive;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return accountActive == user.accountActive && Objects.equals(id, user.id) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && role == user.role && status == user.status && Objects.equals(createdAt, user.createdAt) && Objects.equals(updatedAt, user.updatedAt);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, email, password, role, status, createdAt, updatedAt, accountActive);
	}
}
