package com.homebaby.requests;

import jakarta.validation.constraints.NotBlank;

public record PasswordRecoveryRequest(
    @NotBlank
    String password,

    @NotBlank
    String passwordConfirmation
) {
}
