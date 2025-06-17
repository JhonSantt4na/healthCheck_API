package com.santt4na.health_check.mapper;

import com.santt4na.health_check.dto.securityDTO.AccountCredentialsDTO;
import com.santt4na.health_check.entity.security.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	@Mapping(source = "fullname", target = "fullName")
	@Mapping(source = "username", target = "userName")
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "permissions", ignore = true)
	@Mapping(target = "accountNonExpired", ignore = true)
	@Mapping(target = "accountNonLocked", ignore = true)
	@Mapping(target = "credentialsNonExpired", ignore = true)
	@Mapping(target = "enabled", ignore = true)
	public abstract User toEntity(AccountCredentialsDTO dto);
	
	public abstract AccountCredentialsDTO toDTO(User user);
}
