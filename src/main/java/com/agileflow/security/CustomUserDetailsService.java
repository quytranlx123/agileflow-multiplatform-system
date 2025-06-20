package com.agileflow.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.agileflow.entity.User;
import com.agileflow.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		try {
			User user = userRepository.findByEmail(email)
					.orElseThrow(() -> new UsernameNotFoundException("Email không tồn tại: " + email));

			boolean isActive = Boolean.TRUE.equals(user.getIsActive());

			Set<GrantedAuthority> authorities = user.getRoles().stream()
					.map(role -> new SimpleGrantedAuthority(role.getName())) // name = "ROLE_ADMIN"
					.collect(Collectors.toSet());

			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPasswordHash(),
					isActive, // enabled
					true, // accountNonExpired
					true, // credentialsNonExpired
					true, // accountNonLocked
					authorities);
		} catch (Exception e) {
			System.err.println("Lỗi trong loadUserByUsername: " + e.getMessage());
			e.printStackTrace();
			throw new InternalAuthenticationServiceException("Lỗi nội bộ khi load user", e);
		}
	}

}
