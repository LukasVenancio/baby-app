package com.homebaby.requests;

import com.homebaby.entities.User;
import com.homebaby.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateChildRequest(
    @NotNull
    @Schema(example = "2024-04-11T13:17:51.701Z", requiredMode = Schema.RequiredMode.REQUIRED)
    LocalDateTime birthDate,

    @NotNull
    @Schema(example = "Cassio", requiredMode = Schema.RequiredMode.REQUIRED)
    String name,

    @NotNull
    @Schema(example = "MALE", requiredMode = Schema.RequiredMode.REQUIRED)
    Gender gender
) {
}
