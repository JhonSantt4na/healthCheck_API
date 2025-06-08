package com.santt4na.health_check.mapper;

import com.santt4na.health_check.dto.securityDTO.UserRegisterDTO;
import com.santt4na.health_check.dto.securityDTO.UserResponseDTO;
import com.santt4na.health_check.entity.security.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "status", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "accountActive", ignore = true)
	User registerToEntity(UserRegisterDTO dto);
	
	UserResponseDTO responseToDto(User user);
}