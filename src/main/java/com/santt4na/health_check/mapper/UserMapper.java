package com.santt4na.health_check.mapper;

import com.santt4na.health_check.dto.securityDTO.AccountCredentialsDTO;
import com.santt4na.health_check.entity.security.Permission;
import com.santt4na.health_check.entity.security.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	@Mapping(target = "authorities", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "accountNonExpired", constant = "true")
	@Mapping(target = "accountNonLocked", constant = "true")
	@Mapping(target = "credentialsNonExpired", constant = "true")
	@Mapping(target = "enabled", constant = "true")
	User toEntity(AccountCredentialsDTO dto);
	
	AccountCredentialsDTO toDTO(User user);
}
