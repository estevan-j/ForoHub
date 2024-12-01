package com.ForoHub.ForoAPI.domain.posts;

import jakarta.validation.constraints.NotBlank;

public record PostData(
        @NotBlank
        String message,
        String createdAt,
        @NotBlank
        String topic,
        String author
) {
}
