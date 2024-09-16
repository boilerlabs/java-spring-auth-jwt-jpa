package com.boilerlabs.auth.records;

import java.util.Set;
import java.util.UUID;

public record UserDetailsRecord(UUID id, String username, Set<String> roles) {
}
