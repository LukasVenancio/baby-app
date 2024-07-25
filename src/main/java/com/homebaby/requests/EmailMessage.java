package com.homebaby.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record EmailMessage(
        @NotNull
        List<String> receivers,

        @NotBlank
        String subject,

        @NotBlank
        String content
) {
}
