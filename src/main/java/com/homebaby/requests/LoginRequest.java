package com.homebaby.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest (
        @Email
        @Schema(example = "jorge@gmail", requiredMode = Schema.RequiredMode.REQUIRED)
        String email,

        @NotBlank
        String password
){
}
