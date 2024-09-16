package com.boilerlabs.auth.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boilerlabs.auth.entities.User;

public interface UserRepository extends JpaRepository<User, UUID> {
}