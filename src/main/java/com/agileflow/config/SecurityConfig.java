package com.agileflow.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Component
	public class NoCacheFilter implements Filter {
		@Override
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
				throws IOException, ServletException {
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			httpResponse.setHeader("Pragma", "no-cache");
			httpResponse.setHeader("Expires", "0");
			chain.doFilter(request, response);
		}
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(
						auth -> auth.requestMatchers("/login", "/register", "/css/**", "/js/**", "/images/**")
								.permitAll().requestMatchers("/admin/**").hasRole("ADMIN").requestMatchers("/user/**")
								.hasAnyRole("USER", "ADMIN").anyRequest().authenticated())
				.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/", true).permitAll())
				.logout(logout -> logout.invalidateHttpSession(true).clearAuthentication(true)
						.deleteCookies("JSESSIONID") // Xóa cookie session
						.logoutSuccessUrl("/login?logout").permitAll())
				.headers(headers -> headers.cacheControl().disable().frameOptions().disable());

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
