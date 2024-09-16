package com.boilerlabs.auth.records;

import java.time.Instant;

public record SignInResponseRecord(String token, Instant expiration) {
}
