
package com.sridar.codereview.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CodeReviewRequest(

        @NotBlank(message = "Language is required")
        @Size(max = 50, message = "Language name is too long")
        String language,

        @NotBlank(message = "Code cannot be empty")
        @Size(max = 20000, message = "Code exceeds the maximum allowed length of 20000 characters")
        String code

) {}
