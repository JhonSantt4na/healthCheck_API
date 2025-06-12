package com.santt4na.health_check.controller.docs;


import com.santt4na.health_check.dto.securityDTO.UserResponseDTO;
import com.santt4na.health_check.dto.securityDTO.TokenResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public interface UserControllerDocs {
	
	@Operation(
		summary = "Find User by Email",
		description = "Retrieve a user based on their email address.",
		tags = {"User"},
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "User found",
				content = @Content(
					mediaType = MediaType.APPLICATION_JSON_VALUE,
					schema = @Schema(implementation = UserResponseDTO.class)
				)
			),
			@ApiResponse(responseCode = "204", description = "No content", content = @Content),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
			@ApiResponse(responseCode = "404", description = "User not found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
		}
	)
	ResponseEntity<UserResponseDTO> findEmail(@Parameter(description = "User Email") String email);
	
	@Operation(
		summary = "Check if Email Exists",
		description = "Verify if a user with the specified email exists.",
		tags = {"User"},
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "Check completed",
				content = @Content(
					mediaType = MediaType.APPLICATION_JSON_VALUE,
					schema = @Schema(type = "boolean", example = "true")
				)
			),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
		}
	)
	boolean emailExists(@Parameter(description = "User Email") String email);
	
	@Operation(
		summary = "Authenticate User",
		description = "Authenticate the user and return a token upon success.",
		tags = {"User"},
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "Authentication successful",
				content = @Content(
					mediaType = MediaType.APPLICATION_JSON_VALUE,
					schema = @Schema(implementation = TokenResponseDTO.class)
				)
			),
			@ApiResponse(responseCode = "400", description = "Invalid credentials", content = @Content),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
		}
	)
	ResponseEntity<String> authenticate(
		@Parameter(description = "User Email") String email,
		@Parameter(description = "User Password") String password
	);
	
	@Operation(
		summary = "Change User Password",
		description = "Allows the user to change their password using the user ID.",
		tags = {"User"},
		responses = {
			@ApiResponse(responseCode = "204", description = "Password changed successfully", content = @Content),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
			@ApiResponse(responseCode = "404", description = "User not found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
		}
	)
	ResponseEntity<Void> replacePassword(
		@Parameter(description = "User ID") Long userId,
		@Parameter(description = "New Password") String newPassword
	);
}