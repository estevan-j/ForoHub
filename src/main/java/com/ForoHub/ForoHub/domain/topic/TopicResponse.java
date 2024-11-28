package com.ForoHub.ForoHub.domain.topic;

import com.ForoHub.ForoHub.domain.Status;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record TopicResponse(
        Long id,
        String title,
        String message,
        String author,
        String course,
        Status status,
        LocalDate creationData
) {
}
