package com.agileflow.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	private UUID id;
	private String email;
	private String fullName;
	private String passwordHash;
	private Boolean isActive = true;
	private String avatarUrl;

}
