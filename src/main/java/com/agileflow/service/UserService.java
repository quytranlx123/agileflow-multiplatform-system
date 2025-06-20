package com.agileflow.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.agileflow.dto.UserDto;

public interface UserService {
	List<UserDto> getAll();

	Optional<UserDto> getById(UUID id);

	UserDto create(UserDto dto, MultipartFile avatar);

	Optional<UserDto> update(UUID id, UserDto dto);

	void delete(UUID id);

}
