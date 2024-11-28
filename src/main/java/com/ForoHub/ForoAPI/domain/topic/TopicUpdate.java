package com.ForoHub.ForoAPI.domain.topic;

import jakarta.validation.constraints.NotBlank;

public record TopicUpdate(
        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotBlank
        String course
) {
}
