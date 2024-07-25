package com.homebaby.requests;

import com.homebaby.enums.FamilyType;
import com.homebaby.enums.IncomeType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateFamilyRequest(
        @NotNull
        @Schema(example = "ADOPTIVE_FAMILY", requiredMode = Schema.RequiredMode.REQUIRED)
        FamilyType familyType,

        @NotNull
        @Schema(example = "TWO_THOUSAND_AND_ONE_TO_THREE_THOUSAND", requiredMode = Schema.RequiredMode.REQUIRED)
        IncomeType incomeType
) {
}
