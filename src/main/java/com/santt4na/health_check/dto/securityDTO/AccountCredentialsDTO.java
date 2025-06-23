package com.santt4na.health_check.dto.securityDTO;

import com.santt4na.health_check.entity.security.Permission;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Email;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
public class AccountCredentialsDTO implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	private String userName;
	
	@NotBlank
	private String fullName;
	
	@Email
	@NotBlank
	private String email;
	
	@NotBlank
	private String password;
	
	private List<Permission> permissions = new ArrayList<>();
	
	public AccountCredentialsDTO(String userName, String password, String fullName, String email) {
	}
	
	public AccountCredentialsDTO() {
		this.permissions = new ArrayList<>();
	}
	
	public List<String> getRoles() {
		List<String> roles = new ArrayList<>();
		if (permissions != null) {
			for (Permission permission : permissions) {
				roles.add(permission.getDescription());
			}
		}
		return roles;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AccountCredentialsDTO that = (AccountCredentialsDTO) o;
		return Objects.equals(userName, that.userName) &&
			Objects.equals(fullName, that.fullName) &&
			Objects.equals(email, that.email) &&
			Objects.equals(password, that.password) &&
			Objects.equals(permissions, that.permissions);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(userName, fullName, email, password, permissions);
	}
}