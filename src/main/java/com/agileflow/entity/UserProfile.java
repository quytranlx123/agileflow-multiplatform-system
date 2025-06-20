package com.agileflow.entity;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

	@Id
	private UUID id;

	@OneToOne
	@MapsId // dùng chung ID với User
	@JoinColumn(name = "id")
	private User user;

	private LocalDate birthDay;
	private String address;
	private String phone;
	private String gender;
}
