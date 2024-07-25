package com.homebaby.responses.gestation;

import com.homebaby.enums.Gender;
import com.homebaby.enums.GestationType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

public record GestationResponse(
        UUID id,

        @Schema(example = "2024-04-11T13:17:51.701Z", requiredMode = Schema.RequiredMode.REQUIRED)
        LocalDateTime lastMenstruation,

        @Schema(example = "2025-01-12T20:03:22", requiredMode = Schema.RequiredMode.REQUIRED)
        LocalDateTime probableBirthDate,

        @Schema(example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        Integer pregnancyNumber,

        @Schema(example = "false", requiredMode = Schema.RequiredMode.REQUIRED)
        Boolean hadAbortion,

        @Schema(example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        Integer abortionNumber,

        @Schema(example = "SPONTANEOUS", requiredMode = Schema.RequiredMode.REQUIRED)
        GestationType gestationType,

        @Schema(example = "Alice")
        String babyName,

        @Schema(example = "FEMALE")
        Gender babyGender
) {
}
