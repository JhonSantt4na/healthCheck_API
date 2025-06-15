package com.santt4na.health_check.dto.securityDTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
public class TokenDTO implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private Boolean authenticated;
	private Date created;
	private Date expiration;
	private String accessToken;
	private String refreshToken;
	
	public TokenDTO() {
	}
	
	public TokenDTO(String userName, Boolean authenticated, Date created, Date expiration, String accessToken, String refreshToken) {
		this.userName = userName;
		this.authenticated = authenticated;
		this.created = created;
		this.expiration = expiration;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		TokenDTO tokenDTO = (TokenDTO) o;
		return Objects.equals(userName, tokenDTO.userName) && Objects.equals(authenticated, tokenDTO.authenticated) && Objects.equals(created, tokenDTO.created) && Objects.equals(expiration, tokenDTO.expiration) && Objects.equals(accessToken, tokenDTO.accessToken) && Objects.equals(refreshToken, tokenDTO.refreshToken);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(userName, authenticated, created, expiration, accessToken, refreshToken);
	}
}
