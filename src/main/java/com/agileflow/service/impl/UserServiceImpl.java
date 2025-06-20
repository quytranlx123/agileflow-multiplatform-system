package com.agileflow.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.agileflow.dto.UserDto;
import com.agileflow.entity.User;
import com.agileflow.repository.UserRepository;
import com.agileflow.service.UserService;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final S3Client s3Client;
	@Autowired
	private final UserRepository userRepository;
	private final ModelMapper mapper;
	private final BCryptPasswordEncoder passwordEncoder;

	@Override
	public List<UserDto> getAll() {
		return userRepository.findAll().stream().map(user -> mapper.map(user, UserDto.class)).toList();
	}

	@Override
	public Optional<UserDto> getById(UUID id) {
		return userRepository.findById(id).map(user -> mapper.map(user, UserDto.class));
	}

	@Override
	public UserDto create(UserDto dto, MultipartFile avatar) {
		User user = mapper.map(dto, User.class);
		user.setPasswordHash(passwordEncoder.encode(dto.getPasswordHash()));
		if (avatar != null && !avatar.isEmpty()) {
			// Tạo tên file duy nhất
			String fileName = UUID.randomUUID() + "_" + avatar.getOriginalFilename();

			// Upload lên S3
			String avatarUrl = uploadAvatarToS3(avatar, fileName);

			// Lưu URL vào user
			user.setAvatarUrl(avatarUrl);
		}
		User savedUser = userRepository.save(user);
		return mapper.map(savedUser, UserDto.class);
	}

	@Override
	public Optional<UserDto> update(UUID id, UserDto dto) {
		return userRepository.findById(id).map(existing -> {
			mapper.map(dto, existing);
			User updated = userRepository.save(existing);

			return mapper.map(updated, UserDto.class);
		});

	}

	@Override
	public void delete(UUID id) {
		userRepository.deleteById(id);

	}

	// Hàm bên thứ 3
	private String uploadAvatarToS3(MultipartFile file, String fileName) {
		try {
			// Giả sử bạn đã inject S3Client hoặc S3Presigner
			PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket("your-bucket-name")
					.key("avatars/" + fileName).contentType(file.getContentType()).build();

			s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));

			// Trả về URL public hoặc presigned URL
			return "https://your-bucket-name.s3.amazonaws.com/avatars/" + fileName;
		} catch (IOException e) {
			throw new RuntimeException("Upload avatar failed", e);
		}
	}

}
