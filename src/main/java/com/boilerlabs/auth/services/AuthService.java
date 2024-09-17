package com.boilerlabs.auth.services;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boilerlabs.auth.entities.Role;
import com.boilerlabs.auth.entities.User;
import com.boilerlabs.auth.records.SignInRequestRecord;
import com.boilerlabs.auth.records.SignInResponseRecord;
import com.boilerlabs.auth.records.SignUpRequestRecord;
import com.boilerlabs.auth.repositories.RoleRepository;
import com.boilerlabs.auth.repositories.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(SignUpRequestRecord signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.username())) {
            throw new RuntimeException("Username already taken");
        }

        User user = new User();
        user.setUsername(signUpRequest.username());
        user.setPassword(passwordEncoder.encode(signUpRequest.password()));
        user.setRoles(Set.of(roleRepository.findByName("ROLE_USER").orElseThrow()));

        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public SignInResponseRecord signIn(SignInRequestRecord signInRequest) {
        User user = userRepository.findByUsername(signInRequest.username())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(signInRequest.password(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        String token = createToken(user);
        Instant expiration = Instant.now().plus(Duration.ofHours(1));

        return new SignInResponseRecord(token, expiration);
    }

    private String createToken(User user) {
        try {
            JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                    .issuer("boilerlabs")
                    .subject(user.getId().toString())
                    .claim("roles", user.getRoles().stream().map(Role::getName).toList())
                    .issuedAt(Instant.now())
                    .expiresAt(Instant.now().plus(Duration.ofHours(1)))
                    .build();

            return jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create JWT token", e);
        }
    }
}
