package com.homebaby.responses.user;

import com.homebaby.enums.MaritalStatus;
import com.homebaby.responses.child.ChildSimpleResponse;
import com.homebaby.responses.family.FamilyResponse;
import com.homebaby.responses.gestation.GestationSimpleResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record UserDetailedResponse(
        UUID id,

        @Schema(example = "Jorge", requiredMode = Schema.RequiredMode.REQUIRED)
        String firstName,

        @Schema(example = "Lukas", requiredMode = Schema.RequiredMode.REQUIRED)
        String lastName,

        @Schema(example = "2024-04-11T13:17:51.701Z", requiredMode = Schema.RequiredMode.REQUIRED)
        LocalDateTime birthDate,

        @Schema(example = "jorge@gmail", requiredMode = Schema.RequiredMode.REQUIRED)
        String email,

        @Schema(example = "MARRIED", requiredMode = Schema.RequiredMode.REQUIRED)
        MaritalStatus maritalStatus,

        FamilyResponse family,

        List<GestationSimpleResponse> gestations,

        List<ChildSimpleResponse> children
) {
}
