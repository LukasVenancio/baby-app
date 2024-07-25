package com.homebaby.errors;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@With
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    @Schema(description = "Simple message to identify the problem")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ExceptionCode error;
    @Schema(description = "Error description")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String[] details;
}
