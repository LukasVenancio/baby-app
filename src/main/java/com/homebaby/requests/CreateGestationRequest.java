package com.homebaby.requests;

import com.homebaby.enums.Gender;
import com.homebaby.enums.GestationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NonNull;

import java.time.LocalDateTime;

public record CreateGestationRequest(
        @NonNull
        @Schema(example = "2024-04-11T13:17:51.701Z", requiredMode = Schema.RequiredMode.REQUIRED)
        LocalDateTime lastMenstruation,

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

