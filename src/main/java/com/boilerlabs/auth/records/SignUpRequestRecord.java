package com.boilerlabs.auth.records;

import jakarta.validation.constraints.NotBlank;

public record SignUpRequestRecord(
        @NotBlank(message = "Sign Up Request username cannot be blank") String username,
        @NotBlank(message = "Sign Up Request password cannot be blank") String password) {
}