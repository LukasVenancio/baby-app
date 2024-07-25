package com.homebaby.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class VideoFilter{
    @Schema(defaultValue = "1")
    private int page = 1;

    @Schema(defaultValue = "30", minimum = "5", maximum = "50")
    @Min(value = 5, message = "the minimum value accept is 5")
    @Max(50)
    private int limit = 30;

    private String search;

    private List<String> youtubeIds;
}
