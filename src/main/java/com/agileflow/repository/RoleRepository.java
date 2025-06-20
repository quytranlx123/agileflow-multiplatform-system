package com.agileflow.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agileflow.entity.Role;

public interface RoleRepository extends JpaRepository<Role, UUID> {

	Optional<Role> findByName(String name);

	boolean existsByName(String name);

}
