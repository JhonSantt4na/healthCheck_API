package com.santt4na.health_check.dto.securityDTO;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class AccountCredentialsDTO implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String password;
	
	public AccountCredentialsDTO() {
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		AccountCredentialsDTO that = (AccountCredentialsDTO) o;
		return Objects.equals(userName, that.userName) && Objects.equals(password, that.password);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(userName, password);
	}
}
