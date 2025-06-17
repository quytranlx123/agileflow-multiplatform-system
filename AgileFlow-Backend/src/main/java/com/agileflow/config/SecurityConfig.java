package com.agileflow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()) // Tắt CSRF (nên bật khi dùng POST/PUT)
				.authorizeHttpRequests(auth -> auth
						// Cho phép truy cập không cần đăng nhập đối với các đường dẫn cần thiết:
						.requestMatchers("/login", "/register", "/css/**", "/js/**", "/images/**").permitAll()

						// Các đường dẫn khác yêu cầu xác thực:
						.requestMatchers("/admin/**").hasRole("ADMIN").requestMatchers("/user/**")
						.hasAnyRole("USER", "ADMIN")

						// Tất cả các yêu cầu còn lại phải xác thực:
						.anyRequest().authenticated())
				.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/", true).permitAll())
				.logout(logout -> logout.invalidateHttpSession(true).clearAuthentication(true)
						.logoutSuccessUrl("/login?logout").permitAll());

		return http.build();
	}

	// Cấu hình user tạm trong bộ nhớ
	@Bean
	org.springframework.security.core.userdetails.UserDetailsService userDetailsService() {
		var user1 = org.springframework.security.core.userdetails.User.withUsername("admin").password("123456")
				.roles("ADMIN").build();

		var user2 = org.springframework.security.core.userdetails.User.withUsername("user").password("123456")
				.roles("USER").build();

		return new org.springframework.security.provisioning.InMemoryUserDetailsManager(user1, user2);
	}

	// Cấu hình PasswordEncoder (dùng NoOp để test, không nên dùng thực tế)
	@SuppressWarnings("deprecation")
	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
