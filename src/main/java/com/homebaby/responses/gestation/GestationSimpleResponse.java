package com.homebaby.responses.gestation;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

public record GestationSimpleResponse(
        UUID id,

        @Schema(example = "2024-04-11T13:17:51.701Z", requiredMode = Schema.RequiredMode.REQUIRED)
        LocalDateTime probableBirthDate
) {
}
