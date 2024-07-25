package com.homebaby.responses.childbirth;

import com.homebaby.enums.ChildbirthType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record ChildbirthResponse(
        UUID id,

        @Schema(example = "NORMAL", requiredMode = Schema.RequiredMode.REQUIRED)
        ChildbirthType childbirthType,

        @Schema(example = "1.2", requiredMode = Schema.RequiredMode.REQUIRED)
        Double weight,

        @Schema(example = "0.8", requiredMode = Schema.RequiredMode.REQUIRED)
        Double height,

        @Schema(example = "Não teve nenhuma complicação", requiredMode = Schema.RequiredMode.REQUIRED)
        String intercurrence,

        @Schema(example = "8", requiredMode = Schema.RequiredMode.REQUIRED)
        Integer apgarFirstMinute,

        @Schema(example = "5", requiredMode = Schema.RequiredMode.REQUIRED)
        Integer apgarFifthMinute
) {
}
