package com.ForoHub.ForoAPI.domain.posts;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostUpdateData(
        @NotNull
        Long id,
        @NotBlank
        String message
) {
}
