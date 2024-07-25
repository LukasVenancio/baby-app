package com.homebaby.responses.family;

import com.homebaby.enums.FamilyType;
import com.homebaby.enums.IncomeType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record FamilyResponse(
        UUID id,

        @Schema(example = "ADOPTIVE_FAMILY", requiredMode = Schema.RequiredMode.REQUIRED)
        FamilyType familyType,

        @Schema(example = "TWO_THOUSAND_AND_ONE_TO_THREE_THOUSAND", requiredMode = Schema.RequiredMode.REQUIRED)
        IncomeType incomeType
) { }
