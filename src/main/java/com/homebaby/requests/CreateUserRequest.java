package com.homebaby.requests;

import com.homebaby.enums.MaritalStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record CreateUserRequest(
        @NotBlank
        @Schema(example = "Jorge", requiredMode = Schema.RequiredMode.REQUIRED)
        String firstName,

        @NotBlank
        @Schema(example = "Lukas", requiredMode = Schema.RequiredMode.REQUIRED)
        String lastName,

        @NotNull
        @Schema(example = "2024-04-11T13:17:51.701Z", requiredMode = Schema.RequiredMode.REQUIRED)
        LocalDateTime birthDate,

        @Email
        @Schema(example = "jorge@gmail", requiredMode = Schema.RequiredMode.REQUIRED)
        String email,

        @NotBlank
        String password,

        @NotNull
        @Schema(example = "MARRIED", requiredMode = Schema.RequiredMode.REQUIRED)
        MaritalStatus maritalStatus,

        @NotNull
        CreateFamilyRequest family,

        @NotNull
        List<CreateChildRequest> children,

        @NotNull
        List<CreateGestationRequest> gestations
) {
}
