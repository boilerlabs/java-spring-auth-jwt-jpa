package com.boilerlabs.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.boilerlabs.auth.entities.Role;
import com.boilerlabs.auth.repositories.RoleRepository;

@Configuration
public class DataInitializer {

    @Autowired
    private RoleRepository roleRepository;

    @Bean
    public CommandLineRunner initializeRoles() {
        return args -> {
            if (!roleRepository.existsByName("ROLE_USER")) {
                roleRepository.save(new Role("ROLE_USER"));
            }

            if (!roleRepository.existsByName("ROLE_ADMIN")) {
                roleRepository.save(new Role("ROLE_ADMIN"));
            }
        };
    }
}