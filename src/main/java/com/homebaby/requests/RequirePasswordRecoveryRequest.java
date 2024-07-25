package com.homebaby.requests;

import jakarta.validation.constraints.Email;

public record RequirePasswordRecoveryRequest(
        @Email
        String email
) {
}
