package com.homebaby.responses.child;

import com.homebaby.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

public record ChildResponse(
        UUID id,

        @Schema(example = "2024-04-11T13:17:51.701Z", requiredMode = Schema.RequiredMode.REQUIRED)
        LocalDateTime birthDate,

        @Schema(example = "Cassio", requiredMode = Schema.RequiredMode.REQUIRED)
        String name,

        @Schema(example = "MALE", requiredMode = Schema.RequiredMode.REQUIRED)
        Gender gender
) { }
