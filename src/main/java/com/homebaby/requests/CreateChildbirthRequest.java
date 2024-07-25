package com.homebaby.requests;

import com.homebaby.enums.ChildbirthType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateChildbirthRequest(
        @NotNull
        @Schema(example = "NORMAL", requiredMode = Schema.RequiredMode.REQUIRED)
        ChildbirthType childbirthType,

        @NotNull
        @Schema(example = "1.2", requiredMode = Schema.RequiredMode.REQUIRED)
        Double weight,

        @NotNull
        @Schema(example = "0.8", requiredMode = Schema.RequiredMode.REQUIRED)
        Double height,

        @Schema(example = "Não teve nenhuma complicação", requiredMode = Schema.RequiredMode.REQUIRED)
        String intercurrence,

        @NotNull
        @Schema(example = "8", requiredMode = Schema.RequiredMode.REQUIRED)
        Integer apgarFirstMinute,

        @NotNull
        @Schema(example = "5", requiredMode = Schema.RequiredMode.REQUIRED)
        Integer apgarFifthMinute,

        @NotNull
        UUID userId
) {
}
