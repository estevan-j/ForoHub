package com.ForoHub.ForoAPI.domain.posts;

import java.time.LocalDateTime;

public record PostResponse(
        Long id,
        String message,
        LocalDateTime createdAt,
        String author,
        String topic
) {
}
